/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;


import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.python.core.PyException;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

/**
 *
 * @author doyin
 */
public class Excelkuli {

    private static final String step = "step";
    private static final String pre_script = "pre_engin_script";
    private static final String after_script = "after_engin_script";
    private static final String python_enginfile = "testEngin.py";
    /**
     * paramter for invoke method
     */
    private static final String sharp_symbol = "#";
    /**
     * for run script
     */
    private static final String atmark_symbol = "@";
    private static final String after_suffix = "after";
    private static final String pre_suffix = "pre";
    private static final String key_parameters = "parameters";
    private static final String key_afterscript = "after_step_script";
    static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Excelkuli.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        
        if (args.length == 0) {
            System.out.println("Usage: Excelsikuli excelfile [sheetname]|[sheetname,sheetname,sheetname]");
            return;
        }
        log.info("read the excel file " + args[0]);
        // create XSSFWorkbook 
        XSSFWorkbook xwb = new XSSFWorkbook(args[0]);

        // PythonInterpreter
        PythonInterpreter interpreter;

        XSSFSheet sheet;
        Map cellcontentForuseLater;
        if (args.length == 1) {
            for (int i = 0; i < 1000; i++) {
                // read sheets
                try {
                    sheet = xwb.getSheetAt(i);
                    log.info("run " + sheet.getSheetName());
                } catch (Exception e) {
                    break;
                }
                cellcontentForuseLater = new HashMap();
                log.info("recreate PythonInterpreter");
                interpreter = new PythonInterpreter();
                runCase(cellcontentForuseLater, interpreter, sheet);
            }
        } else {
            String cases = args[1];
            String[] casesArr = cases.split(",");
            for (int j = 0; j < casesArr.length; j++) {
                try {
                    sheet = xwb.getSheet(casesArr[j]);
                    log.info("run " + sheet.getSheetName());
                } catch (Exception e) {
                    log.error("case not found " + casesArr[j]);
                    break;
                }
                cellcontentForuseLater = new HashMap();
                log.info("recreate PythonInterpreter");
                interpreter = new PythonInterpreter();
                runCase(cellcontentForuseLater, interpreter, sheet);
            }
        }


    }

    private static void runCase(Map cellcontentForuseLater, PythonInterpreter interpreter, XSSFSheet sheet) {
        log.info("Python engin loaded");
        interpreter.execfile(python_enginfile);
        try {
            readSheetAndInvokPy(sheet, interpreter, cellcontentForuseLater);
        } catch (PyException uin) {

            if(uin.value.toString()!=null&&uin.value.toString().contains("UserInterruption")){
                log.info("UserInterruption happened");
            }else{
                throw uin;
            }
           
        }
        if (cellcontentForuseLater.containsKey(after_script)) {
            String script = cellcontentForuseLater.get(after_script).toString();
           
            interpreter.exec(script);
            log.info("executed after_engin_script");
        }
    }

    private static void readSheetAndInvokPy(XSSFSheet sheet, PythonInterpreter interpreter, Map cellcontentForuseLater) throws PyException{
        // loop the contents of sheet

        boolean isNextRowdata = false;

        
        int rowsWihtData=sheet.getPhysicalNumberOfRows();
        
        if(rowsWihtData<=0){
            return;
        }
        int rowRange=rowsWihtData;
        for (int i = sheet.getFirstRowNum(); i < rowRange; i++) {
            XSSFRow row = sheet.getRow(i);
           
            if (row==null){
                rowRange=rowRange+1;
                
                continue;
            }
            for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
                
                if (j == -1) {
                    break;
                }

                String cellvalue = row.getCell(j, Row.CREATE_NULL_AS_BLANK).toString();

                if (pre_script.equals(cellvalue)) {
                    XSSFCell script = row.getCell(j + 1, Row.CREATE_NULL_AS_BLANK);
                    if (script != null && !script.equals("")) {

                        interpreter.exec(script.toString());
                        log.info("executed pre_engin_script");
                    }
                } else if (after_script.equals(cellvalue)) {

                    String script = row.getCell(j + 1, Row.CREATE_NULL_AS_BLANK).toString();
                    if (script != null && !script.equals("")) {
                        cellcontentForuseLater.put(after_script, script);
                    }

                } else if (step.equals(cellvalue)) {

                    for (int k = 0; k < row.getPhysicalNumberOfCells(); k++) {

                        String ctnt = row.getCell(k, Row.CREATE_NULL_AS_BLANK).toString();
                        if (ctnt != null && !"".equals(ctnt)) {
                            if (ctnt.contains(sharp_symbol)) {
                                cellcontentForuseLater.put(sharp_symbol + ctnt.split(sharp_symbol)[1], k);
                            } else if (ctnt.contains(atmark_symbol)) {
                                cellcontentForuseLater.put(atmark_symbol + ctnt.split(atmark_symbol)[1], k);
                            }
                        }
                    }
                    isNextRowdata = true;
                } else if (isNextRowdata && j == 0 && doubleNum(cellvalue) > 0) {//step读取

					Map mp = editScriptAndRunPreStepScript(cellcontentForuseLater, row, interpreter);
					log.info("invoke extendWaitEvent(" + mp.get(key_parameters).toString() + ")");
					interpreter.exec("extendWaitEvent(" + mp.get(key_parameters).toString() + ")");
					
					if (mp.get(key_afterscript) != null) {

						interpreter.exec(mp.get(key_afterscript).toString());

					}
                    


                }

            }

        }

    }

    private static Map editScriptAndRunPreStepScript(Map cellcontentForuseLater, XSSFRow row, PythonInterpreter interpreter) {
        StringBuffer bf = new StringBuffer();
        Map resMap = new HashMap();
        Iterator it = cellcontentForuseLater.keySet().iterator();
        // find # and @ column
        while (it.hasNext()) {
            String parameter = it.next().toString();
            if (parameter.contains(sharp_symbol)) {
                int idx = Integer.parseInt(cellcontentForuseLater.get(parameter).toString());
                String value = row.getCell(idx, Row.CREATE_NULL_AS_BLANK).toString();
                if (value != null && !value.equals("")) {
                    bf.append(parameter.replace(sharp_symbol, "") + "='" + value + "'");
                    bf.append(",");
                }
            } else if (parameter.contains(atmark_symbol + pre_suffix)) {
                int idx = Integer.parseInt(cellcontentForuseLater.get(parameter).toString());
                String value = row.getCell(idx, Row.CREATE_NULL_AS_BLANK).toString();
                if (value != null && !value.equals("")) {

                    interpreter.exec(value);

                }
            } else if (parameter.contains(atmark_symbol + after_suffix)) {
                int idx = Integer.parseInt(cellcontentForuseLater.get(parameter).toString());
                String value = row.getCell(idx, Row.CREATE_NULL_AS_BLANK).toString();
                if (value != null && !value.equals("")) {

                    resMap.put(key_afterscript, value);

                }
            }
        }
        resMap.put(key_parameters, bf.substring(0, bf.length() - 1));

        return resMap;

    }



    private static double doubleNum(String str) {
        double res = 0;
        try {
            res = Double.parseDouble(str.trim());
        } catch (Exception e) {
            return -1;
        }
        return res;
    }
}
