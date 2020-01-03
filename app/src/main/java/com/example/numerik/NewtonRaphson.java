package com.example.numerik;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class NewtonRaphson {
    double x,error;
    int n;
    String formula, formulaTurunan;
    public double[][] list;
    Expression expression;
    NewtonRaphson(double x,double error, int n, String formula){
        this.x=x;
        this.error=error;
        this.n=n;
        this.formula=formula;
        this.list=new double[n][3];
        formulaTurunan="";
        setFormulaTurunan(formula);
    }
    public double[][] getResult(){
        return list;
    }

    public double f(double x){

        String f=formula.replaceAll("x","("+x+")");
        expression = new ExpressionBuilder(f).build();
        return expression.evaluate();
//        return x-Math.exp(-x);
        // return 4*(x*x*x)-15*(x*x)+17*x-6;
    }
    public double g(double x){

        String f=formulaTurunan.replaceAll("x","("+x+")");
        expression = new ExpressionBuilder(f).build();
        return expression.evaluate();
//        return 1+Math.exp(-x);
        // return 3*4*(x*x)-2*15*x+17;
    }
    public void store( int i) {

    }

    public String turunan(String f){
        if(f.indexOf("^")!=-1){
            String[] arr=f.split("\\^");
            return arr[1]+"*"+arr[0]+"^"+(Integer.parseInt(arr[1])-1);
        }else if(f.indexOf("x")!=-1){
            return f.replace("x","1");
        }
        return "0";
    }


    public void setFormulaTurunan(String fx){
        int indexMin=fx.indexOf("-");
        int indexPlus=fx.indexOf("+");
        if(indexMin==-1&&indexPlus==-1){
            formulaTurunan+=turunan(fx);
        }
        else if(indexMin==-1){
            String[] arr=fx.split("\\+",2);
            formulaTurunan+=turunan(arr[0])+"+";
            setFormulaTurunan(arr[1]);
        }
        else if(indexPlus==-1){
            String[] arr=fx.split("\\-",2);
            formulaTurunan+=turunan(arr[0])+"-";
            setFormulaTurunan(arr[1]);
        }
        else {
            if(indexMin<indexPlus){
                String[] arr=fx.split("\\-",2);
                formulaTurunan+=turunan(arr[0])+"-";
                setFormulaTurunan(arr[1]);
            }else if(indexPlus<indexMin){
                String[] arr=fx.split("\\+",2);
                formulaTurunan+=turunan(arr[0])+"+";
                setFormulaTurunan(arr[1]);
            }
        }

    }

    public void rule(int i){
        double f=f(x);
        double g=g(x);
        list[i] = new double[] {x,f,g};
        x=x-(f/g);
    }
    public void run(){
        for(int i=0; i<n; i++){
            rule(i);
        }
    }
}
