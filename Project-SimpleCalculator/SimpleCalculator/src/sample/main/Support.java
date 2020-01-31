/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.main;

import java.util.ArrayList;

/**
 *
 * @author sonho
 */
public class Support {

    public static String sumList(ArrayList<String> listM) {
        double n = 0;
        String result = "";

        for (String x : listM) {
            double tmp = convertDouble(x);
            n = n + tmp;
        }
        if (canHaveTypeInt(n)) {
            result = convertIntNumberFromDouble(n) + "";
        } else {
            result = n + "";
        }
        return result;
    }

    public static String sumListM(ArrayList<String> listMSub, ArrayList<String> listMPlus) {
        double n = 0;
        String result = "";
        double sumListMSub = convertDouble(sumList(listMSub));
        double sumListMPlus = convertDouble(sumList(listMPlus));
        double sum = sumListMPlus + sumListMSub;
        if (canHaveTypeInt(sum)) {
            result = convertIntNumberFromDouble(sum) + "";
        } else {
            result = sum + "";
        }
        return result;
    }

    public static void addToListMSub(ArrayList<String> listMSub, String s) {
        if (!checkType(s).equalsIgnoreCase("string")) {
            listMSub.add("-" + s);
        }
    }

    public static void addToListMPlus(ArrayList<String> listMPlus, String s) {
        if (!checkType(s).equalsIgnoreCase("string")) {
            listMPlus.add(s);
        }
    }

    public static double convertToOppositeNum(double n) {
        return -n;
    }

    public static boolean checkNegativeNum(String s) {
        double n = convertDouble(s);
        if (n >= 0) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkFirstInputIsNegativeNum(ArrayList<String> listInput) {
        if (listInput.size() > 1) {
            if (checkLastInputIsNumber(listInput)) {
                if (checkFirstInputIsSub(listInput)) {
                    return true;
                }
            }
        } else {
            return listInput.get(0).contains("-");
        }
        return false;
    }

    public static double roundDouble(String num) {
        double result = convertDouble(num);
        result = (double) Math.round((result * 100000000)) / 100000000;
        return result;
    }

    public static String calculateSpec(String firstNum, String calculation) {
        if (!checkType(firstNum).equalsIgnoreCase("string")) {
            double num = convertDouble(firstNum);
            String result = "";
            if (calculation.equalsIgnoreCase("√")) {
                if (num >= 0) {
                    num = Math.sqrt(num);
                    int n;
                    if (canHaveTypeInt(num)) {
                        n = convertIntNumberFromDouble(num);
                        result = n + "";
                    } else {
                        result = num + "";
                    }

                } else {
                    result = "ERROR";
                }
            } else if (calculation.equalsIgnoreCase("%")) {
                num = num / 100;
                int n;
                if (canHaveTypeInt(num)) {
                    n = convertIntNumberFromDouble(num);
                    result = n + "";
                } else {
                    result = num + "";
                }
            } else if (calculation.equalsIgnoreCase("1/x")) {
                num = 1 / num;
                int n;
                if (canHaveTypeInt(num)) {
                    n = convertIntNumberFromDouble(num);
                    result = n + "";
                } else {
                    result = num + "";
                }
            }
            return result;
        }
        return "ERROR";
    }

    public static void calculateSpec(ArrayList<String> listInput) {
        //kiem tra xem last input co phai la 1 calculation k
        if (checkLastInputIsCalculation(listInput)) {
            String result = null; //kiem tra xem listInput size co ban 2 hay k la bao gom firstNum va calculation
            if (listInput.size() == 2) {
                result = calculateSpec(listInput.get(0), listInput.get(1));
            } //neu lon hon 2 thi can phai modify firstNum lai
            else if (listInput.size() > 2) {
                modifyFirstNum(listInput);
                result = calculateSpec(listInput.get(0), listInput.get(1));
            }
            if (!result.equalsIgnoreCase("ERROR")) {
                if (checkType(result).equalsIgnoreCase("double")) {
                    result = roundDouble(result) + "";
                }
            }
            listInput.clear();
            listInput.add(result);
        }
    }

    public static boolean checkFirstNumIsNegativeNumber(String s) {
        return s.contains("-");
    }

    public static void modifyInputNumberWhenItIsANegativeNumber(ArrayList<String> listInput) {
        String firstNum = getInputNum(listInput);
        listInput.clear();
        listInput.add(firstNum);
    }

    public static boolean checkFirstInputIsNumber(ArrayList<String> listInput) {
        return checkInputIsNumber(listInput.get(0));
    }

    public static boolean checkFirstInputIsSub(ArrayList<String> listInput) {
        return listInput.get(0).equalsIgnoreCase("-");
    }

    public static String getInputNum(ArrayList<String> listInput) {
        String result = "";
        for (String x : listInput) {
            result = result + x;
        }
        return result;
    }

    public static ArrayList<String> getInputFirstNum(ArrayList<String> listInput) {
        ArrayList<String> result = new ArrayList<>();
        for (String x : listInput) {
            if (!checkInputIsCalculation(x)) {
                result.add(x);
            } else {
                return result;
            }
        }
        return result;
    }

    public static ArrayList<String> getInputSecondNum(ArrayList<String> listInput) {
        ArrayList<String> result = new ArrayList<>();
        int posCalculator = getPositionCalculator(listInput);
        for (int i = posCalculator + 1; i < listInput.size(); i++) {
            result.add(listInput.get(i));
        }
        return result;
    }

    public static int getPositionCalculator(ArrayList<String> listInput) {
        for (int i = 0; i < listInput.size(); i++) {
            if (checkInputIsCalculation(listInput.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public static boolean checkInputNumContainsDot(ArrayList<String> numInput) {
        for (String x : numInput) {
            if (checkInputIsDot(x)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkInputNumContainsDot(String num) {
        String tmp[] = num.split("");
        for (String x : tmp) {
            if (x.equalsIgnoreCase(".")) {
                return true;
            }
        }
        return false;
    }

    public static String getLastInput(ArrayList<String> listInput) {
        return listInput.get(listInput.size() - 1);
    }

    public static String getNearLastInput(ArrayList<String> listInput) {
        return listInput.get(listInput.size() - 2);
    }

    public static boolean checkLastInputIsCalculation(ArrayList<String> listInput) {
        if (getLastInput(listInput).equalsIgnoreCase("+")) {
            return true;
        } else if (getLastInput(listInput).equalsIgnoreCase("-")) {
            return true;
        } else if (getLastInput(listInput).equalsIgnoreCase("x")) {
            return true;
        } else if (getLastInput(listInput).equalsIgnoreCase("÷")) {
            return true;
        } else if (getLastInput(listInput).equalsIgnoreCase("√")) {
            return true;
        } else if (getLastInput(listInput).equalsIgnoreCase("%")) {
            return true;
        } else if (getLastInput(listInput).equalsIgnoreCase("1/x")) {
            return true;
        }
        return false;
    }

    public static boolean checkInputIsCalculation(String x) {
        if (x.equalsIgnoreCase("+")) {
            return true;
        } else if (x.equalsIgnoreCase("-")) {
            return true;
        } else if (x.equalsIgnoreCase("x")) {
            return true;
        } else if (x.equalsIgnoreCase("÷")) {
            return true;
        } else if (x.equalsIgnoreCase("√")) {
            return true;
        } else if (x.equalsIgnoreCase("%")) {
            return true;
        } else if (x.equalsIgnoreCase("1/x")) {
            return true;
        }
        return false;
    }

    public static boolean checkLastInputIsDot(ArrayList<String> listInput) {
        return checkInputIsDot(listInput.get(listInput.size() - 1));
    }

    public static boolean checkInputIsDot(String x) {
        return x.equalsIgnoreCase(".");
    }

    public static boolean checkLastInputIsNumber(ArrayList<String> listInput) {
        return checkInputIsNumber(listInput.get(listInput.size() - 1));
    }

    public static boolean checkInputIsNumber(String x) {
        if (!checkInputIsCalculation(x) && !checkInputIsDot(x)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkContainCalculator(ArrayList<String> listInput) {
        for (String x : listInput) {
            if (checkInputIsCalculation(x)) {
                return true;
            }
        }
        return false;
    }

    public static String combiningTwoInputLast(ArrayList<String> listInput) {
        String s = listInput.get(listInput.size() - 1) + listInput.get(listInput.size() - 2);
        return s;
    }

    public static void modifyInputLastTwoNumber(ArrayList<String> listInput) {
        String s = combiningTwoInputLast(listInput);
        listInput.set(listInput.size() - 2, s);
        listInput.remove(listInput.size() - 1);
    }

    public static void modifyInputLastIsDot(ArrayList<String> listInput) {
        if (checkLastInputIsDot(listInput)) {
            combiningTwoInputLast(listInput);
        }
    }

    public static void modifyFirstNum(ArrayList<String> listInput) {
        if (checkLastInputIsNumber(listInput)) {
            String s = "";
            for (String x : listInput) {
                s = s + x;
            }
            listInput.clear();
            listInput.add(s);
        }
    }

    public static void modifyFirstNumWhenLastInputIsCalculator(ArrayList<String> listInput) {
        if (checkInputIsCalculation(getLastInput(listInput))) {
            ArrayList<String> result = new ArrayList<>();
            String tmp = "";
            for (int i = listInput.size() - 2; i >= 0; i--) {
                tmp = listInput.get(i) + tmp;
            }
            result.add(tmp);
            result.add(getLastInput(listInput));
            listInput.clear();
            for (String x : result) {
                listInput.add(x);
            }
        }
    }

    public static void modifySecondNumWhenLastInputIsNumber(ArrayList<String> listInput) {
        if (!checkInputIsCalculation(getLastInput(listInput))) {
            ArrayList<String> result = new ArrayList<>();
            String tmp = "";
            for (int i = listInput.size() - 1; i >= 2; i--) {
                tmp = listInput.get(i) + tmp;
            }
            result.add(listInput.get(0));
            result.add(listInput.get(1));
            result.add(tmp);
            listInput.clear();
            for (String x : result) {
                listInput.add(x);
            }
        }
    }

    public static void modifySecondNumWhenLastInputIsCalculator(ArrayList<String> listInput) {
        if (checkInputIsCalculation(getLastInput(listInput))) {
            ArrayList<String> result = new ArrayList<>();
            String tmp = "";
            for (int i = listInput.size() - 2; i >= 2; i--) {
                tmp = listInput.get(i) + tmp;
            }
            result.add(listInput.get(0));
            result.add(listInput.get(1));
            result.add(tmp);
            result.add(getLastInput(listInput));
            listInput.clear();
            for (String x : result) {
                listInput.add(x);
            }
        }
    }

    public static boolean isPositiveNum(double n) {
        if (n >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public static String checkType(String n) {
        try {
            Integer.parseInt(n);
            return "int";
        } catch (Exception ex) {
            try {
                Double.parseDouble(n);
                return "double";
            } catch (Exception e) {
                return "string";
            }
        }
    }

    public static boolean canHaveTypeInt(double n) {
        int nAtInt = (int) n;
        if ((n - nAtInt) > 0) {
            return false;
        } else if ((n - nAtInt) == 0) {
            return true;
        }
        return false;
    }

    public static int convertInt(String n) {
        return Integer.parseInt(n);
    }

    public static double convertDouble(String n) {
        return Double.parseDouble(n);
    }

    public static int convertIntNumberFromDouble(double n) {
        return (int) n;
    }

    public static int calculateSum(int num1, int num2) {
        return num1 + num2;
    }

    public static double calculateSum(double num1, double num2) {
        return (num1 + num2);
    }

    public static int calculateSub(int num1, int num2) {
        return (num1 - num2);
    }

    public static double calculateSub(double num1, double num2) {
        return (num1 - num2);
    }

    public static int calculateMul(int num1, int num2) {
        return (num1 * num2);
    }

    public static double calculateMul(double num1, double num2) {
        return (num1 * num2);
    }

    public static double calculateDivide(double num1, double num2) {

        return (num1 / num2);
    }

    public static double oppositeNum(double n) {
        return -n;
    }

    public static int oppositeNum(int n) {
        return -n;
    }

    public static double inverseNum(double n) {
        return 1 / n;
    }

    public static double percentNum(double n) {
        return n / 100;
    }

    public static double squareNum(double n) {
        return Math.sqrt(n);
    }

    public static void calculate(ArrayList<String> listInput) {
        if (listInput.size() == 3) {
            double num1 = convertDouble(listInput.get(0));
            double num2 = convertDouble(listInput.get(2));
            double result = 0;
            String calculator = listInput.get(1);
            if (calculator.equalsIgnoreCase("+")) {
                result = calculateSum(num1, num2);
            } else if (calculator.equalsIgnoreCase("-")) {
                result = calculateSub(num1, num2);
            } else if (calculator.equalsIgnoreCase("x")) {
                result = calculateMul(num1, num2);
            } else if (calculator.equalsIgnoreCase("÷")) {
                if (num2 == 0) {
                    listInput.clear();
                    listInput.add("ERROR");
                    return;
                } else {
                    result = calculateDivide(num1, num2);
                }
            }
            listInput.clear();
            if (canHaveTypeInt(result)) {
                int r = (int) result;
                listInput.add(r + "");
            } else {
                listInput.add(result + "");
            }
        } else if (listInput.size() == 4) {
            double num1 = convertDouble(listInput.get(0));
            String calculator = listInput.get(1);
            double num2 = convertDouble(listInput.get(2));
            double result = 0;
            if (calculator.equalsIgnoreCase("+")) {
                result = calculateSum(num1, num2);
            } else if (calculator.equalsIgnoreCase("-")) {
                result = calculateSub(num1, num2);
            } else if (calculator.equalsIgnoreCase("x")) {
                result = calculateMul(num1, num2);
            } else if (calculator.equalsIgnoreCase("÷")) {
                result = calculateDivide(num1, num2);
            }
            String lastCalculator = getLastInput(listInput);
            listInput.clear();
            if (canHaveTypeInt(result)) {
                int r = (int) result;
                listInput.add(r + "");
            } else {
                listInput.add(result + "");
            }
            listInput.add(lastCalculator);
        }
    }

}
