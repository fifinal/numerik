package com.example.numerik;


import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class RegulaFalsi {
    public double a, b, error;
    public int iterasi;
    public double[][] list;

    public double fa, fb, x, fx;
    private String formula;

    Expression expression;

    public RegulaFalsi(double a, double b, double error, int iterasi, String formula){
        this.a=a;
        this.b=b;
        this.error=error;
        this.iterasi=iterasi;
        this.formula=formula;
        this.list=new double[iterasi][6];
    }

    public double f( double x) {
        // return x * Math.exp(-x) + 1;
        String f=formula.replaceAll("x","("+x+")");
        expression = new ExpressionBuilder(f).build();
        return expression.evaluate();
    }

    public void store( int i) {
        list[i-1] = new double[] { a, fa, b, fb, x, fx };
    }

    public double[][] getResult(){
        return list;
    }

    public void rule(int i){
        fa=f(a);
        fb=f(b);

        x=(fb*a-fa*b)/(fb-fa);
        fx=f(x);

        store(i);
        if(fx*fa<0) this.b=x;
        else this.a=x;
    }

    public void run(){

        for(int i=1; i<=iterasi; i++){
            rule(i);
        }
    }
}