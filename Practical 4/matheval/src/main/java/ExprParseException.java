package edu.curtin.matheval;

public class ExprParseException extends Exception
{
    public ExprParseException(String msg)
    {
        super(msg);
    }

    public ExprParseException(String msg, Throwable cause)
    {
        super(msg, cause);
    }
}