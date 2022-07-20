package edu.curtin.matheval;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.logging.Logger;


/**
 * Parses mathematical expressions, and builds a tree of ExprNode objects to represent the parsed 
 * expression.
 */
public class ExprParser
{
    // The regex pattern used for tokenisation purposes. Basically, this skips any amount of 
    // whitespace, then checks for a fractional number (containing and possibly starting with "."),
    // then checks for an integer, and finally falls back to a single other character of any type.
    private static final Pattern TOKEN = Pattern.compile("\\s*([0-9]*\\.[0-9]+|[0-9]+|.)");
    private static final Logger LOGGER = Logger.getLogger(ExprParser.class.getName());
    
    public ExprParser() {}

    /**
     * Parses a string, which is assumed to contain a mathematical expression. Returns the root 
     * node of the resulting object tree.
     */
    public ExprNode parse(String s) throws ExprParseException
    {
        LOGGER.fine(() -> "parse function called with string value " + s);
        List<String> tokens = new LinkedList<>();

        // Tokenise the string, by repeatedly applying the 'TOKEN' regular expression until it 
        // doesn't match anymore (which should only happen at the end of the string).
        String substr = s;
        boolean done = false;
        do
        {
            Matcher matcher = TOKEN.matcher(substr);
            if(matcher.lookingAt())
            {
                tokens.add(matcher.group(1));
                substr = substr.substring(matcher.end());
            }
            else
            {

                done = true;
            }
        }
        while(!done);

        if (tokens.isEmpty())
        {
            throw new ExprParseException("No tokens, String is possibly empty!");
        }
        // Invoke the actual parsing logic. 
        ExprNode node = parseAdd(tokens);

        if (!tokens.isEmpty())
        {
            throw new ExprParseException("Tokens that cannot be processed exist " + tokens);
        }

        return node;
    }

    /** 
     * Parses a sequence of zero-or-more "+" / "-" operators (the lowest operator precedence
     * level).
     */
    private ExprNode parseAdd(List<String> tokens) throws ExprParseException
    {

        LOGGER.fine(() -> "parseAdd function called with string value " + tokens);
        ExprNode node = null;
        try 
        {
            node = parseMul(tokens);
        }
        catch (ExprParseException e)
        {
            throw e;
        }
        
        boolean end = false;
        while(!end && !tokens.isEmpty())
        {
            // Expect next token to be '+' or '-'
            String token = tokens.remove(0);
            LOGGER.fine(() -> "token is " + token);

            switch(token)
            {
                case "+":
                    LOGGER.fine(() -> "Calling the function AddOperator()"); 
                    node = new AddOperator(node, parseMul(tokens));
                    break;
                    
                case "-":
                    LOGGER.fine(() -> "Calling the function SubOperator()"); 
                    node = new SubOperator(node, parseMul(tokens));
                    break;
                    
                default:
                    // The next token isn't "+" or "-", which means we assume this additive 
                    // sequence is over, so push the token back onto the list, and end.
                    LOGGER.fine(() -> "Additive sequence over, token pushed onto the list"); 
                    tokens.add(0, token);
                    end = true;
                    break;
            }
        }
        return node;
    }
    
    /**
     * Parses a sequence of zero-or-more "*" / "/" operators.
     */
    private ExprNode parseMul(List<String> tokens) throws ExprParseException
    {
        ExprNode node = null;
        LOGGER.fine(() -> "parseMul function called with string value " + tokens);
        try
        {
            node = parsePrimary(tokens);
            boolean end = false;
            while(!end && !tokens.isEmpty())
            {
                // Expect next token to be "*" or "/"
                String token = tokens.remove(0);
                LOGGER.fine(() -> "token is " + token);
                switch(token)
                {
                    case "*":
                        LOGGER.fine(() -> "Calling the function MulOperator()");
                        node = new MulOperator(node, parsePrimary(tokens));
                        break;
                        
                    case "/":
                        LOGGER.fine(() -> "Calling the function DivOperator()");
                        node = new DivOperator(node, parsePrimary(tokens));
                        break;
                        
                    default:
                        // The next token isn't "*" or "/", which means we assume this multiplicative
                        // sequence is over, so push the token back onto the list, and end.
                        LOGGER.fine(() -> "Multiplicative sequence over, token pushed back onto list");
                        tokens.add(0, token);
                        end = true;
                        break;
                }
            }
        }
        catch (ExprParseException e)
        {
            throw e;
        }
        catch (IndexOutOfBoundsException e)
        {
            throw new ExprParseException("Abrupt end to the equation, possibly incomplete");
        }
        return node;
    }
    
    /**
     * Parses a "primary" value, which can be either a sub-expression in brackets, a negation "-"
     * operator, a reference to the "x" variable, or a literal number.
     */
    private ExprNode parsePrimary(List<String> tokens) throws ExprParseException
    {
        LOGGER.fine(() -> "parsePrimary function called with string value " + tokens);
        int closingIndex;
        ExprNode node = null;
        String token = tokens.remove(0); // Obtain the next token
        LOGGER.fine(() -> "token is " + token);
        switch(token)
        {
            case "(":
                // Sub-expression inside brackets.
                LOGGER.fine(() -> "Sub-expression inside brackets");
                node = parseAdd(tokens);
                if (!tokens.contains(")"))
                {
                    throw new ExprParseException("Does not close the bracket it has opened!");
                } // Remove closing ")"
                tokens.remove(0);
                break;
                
            case "-":
                // Inverted value (e.g., -(x+1))
                LOGGER.fine(() -> "Negative value so using NegationOperator Decorator");
                node = new NegationOperator(parsePrimary(tokens));
                break;
                
            case "x":
                // Variable value
                LOGGER.fine(() -> "Variable value x in equation creating new XValue object");
                node = new XValue();
                break;
                
            default:
                // Literal number
                try
                {
                    node = new Value(Double.parseDouble(token));
                    LOGGER.fine(() -> "Literal number found in token");
                }
                catch (NumberFormatException e)
                {
                    throw new ExprParseException("Illegal token " + token, e);
                }         
                break;
        }
        return node;
    }
}
