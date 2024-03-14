
import java.io.*;
import java.util.Scanner;

// This is a starter file for QuestionsGame.
//
// Sahithra K & Kyle M
//The purpose of this file is to represent the yes/no tree the computer will use to play the game of 20 questions.

public class QuestionsGame {
    // Your code here
	public QuestionNode overallRoot;

    public static class QuestionNode 
    {
    	public final String data;
    	public QuestionNode left;
    	public QuestionNode right;
    	
    	public QuestionNode(String q)
    	{
    		data = q;
    		left=null;
    		right=null;
    	}
    	
    	
    }
    //
    public QuestionsGame(String object)
    {
       overallRoot = new QuestionNode(object);
       
    }
    
    /*public QuestionsGame(Scanner input)
    {
    	String inp = input.nextLine();
		if(inp.equals("A:")) //If its an answer
		{
			inp = input.nextLine(); //Get the data 
			overallRoot = new QuestionNode(inp);//Set overall root
		
			
			
			
		}
		else if(inp.equals("Q:"))
		{
			inp = input.nextLine();
			overallRoot = new QuestionNode(inp);
			QuestionsGame(input,overallRoot);
	
		}

    }*/
   
    //Purpose is to check the left and the right of the question node
    
    public QuestionsGame(Scanner input)throws Exception
    {
    	
    	createTree(input);
	
    }
    
    private void QuestionsGameHelper(Scanner input, QuestionNode root) throws Exception
    {
    	if(!input.hasNext()) {
    		throw new Exception("End of file");
    	}
    	else
    	{
    	String inp2 = input.nextLine();
    	
    	if(inp2.equals("A:"))
    	{
    		root.left = new QuestionNode(input.nextLine());
    		String text = input.nextLine();
    		
    		if(text.equals("Q:")) 
    		{
    			root.right = new QuestionNode(input.nextLine());
    			QuestionsGameHelper(input, root.right);
    			
    		}
    		else if(text.equals("A:"))
    		{
    			root.right = new QuestionNode(input.nextLine());
    			QuestionsGameHelper(input, overallRoot.right);
    		}
    		root.right = null;
    	}
    	else if(inp2.equals("Q:"))
    	{
    		QuestionNode q1 = new QuestionNode(input.nextLine());
    		String text = input.nextLine();
    		if(text.equals("Q:"))
    		{
    			QuestionNode q2 = new QuestionNode(input.nextLine());
    			root.left = q1; QuestionsGameHelper(input, root.left);
    			root.right = q2; QuestionsGameHelper(input, root.right);
    			
    		}
    		else if(text.equals("A:"))
    		{
    			QuestionNode a = new QuestionNode(input.nextLine());
    			root.left = a;
    			root.right = q1; QuestionsGameHelper(input, root.right);
    					
    		}
    	}
    	}
    	
    
    }
    
      private QuestionNode createTree(Scanner input)
      {
     		String type = input.nextLine();
     		String data = input.nextLine();
     		QuestionNode newNode = new QuestionNode(data);
     		if(type.contains("Q"))
     		{
     			newNode.left=createTree(input);
     			newNode.right=createTree(input);
     		}
     		return newNode;
      }
      
     
   /* {
    	while(input.hasNext())
    	{
    		String in = input.nextLine();
    		
    		if(in.equals("A"))
    		{
    			in=input.nextLine(); //If the line indicates an answer, grab the next line to create a new node
    			QuestionNode n = new QuestionNode(in);
    			QuestionNode temp = overallRoot;
    			while(temp.left!=null)
    			{
    				temp=temp.left;
    			}
    		}
    	}
    } */
    
    /*private QuestionNode findNextQuestion(String currentLine, Scanner file)
    {
    	while(file.hasNext())
    	{
    		String line = file.nextLine();
    		if(line==currentLine)
    		{
    			
    		}
    	}
    }*/
    public void saveQuestions(PrintStream output) throws Exception
    { 
    	//Create a private method to print the tree. it uses PrintStream output and overallRoot
    	//if the node is null, return nothing
    	//if left and right node is not null then it is a question and println Q: in the output file
    	//if not then it is an answer then print whatever data it is.
    	//Finally recurse through the left and right subtree
    	if(output == null)
    	{
    		throw new Exception("PrintStream is null");
    	}
    	printTree(output, overallRoot);
    }
    private void printTree(PrintStream output, QuestionNode root)
    {
    	if(root==null) {return;}
    	
    	if(root.right!=null &&root.left!=null) //Check if it is a leaf
    	{
    		output.println("Q:"); //Then it must be a question so print that type
    	}
    	else
    	{
    		output.println("A:");//It must not be a question so print A
    	}
    	output.println(root.data);//Print the data next
    	//Recurse through left and right due to Pre-order
    	printTree(output, root.left);
    	printTree(output, root.right);
    	
    }
    public void play() 
    {
        Scanner playerInput = new Scanner(System.in);
        QuestionNode current = overallRoot;
        
        while(current.left!=null||current.right!=null)
        {
        	System.out.println(current.data);
        	
        	String response = playerInput.nextLine().toLowerCase().trim();
        	if(response.equals("y"))
        	{
        		if(current.left!=null) {current=current.left;}
        		else {loseGame(playerInput, current);}
        	}
        	else if(response.equals("n"))
        	{
        		if(current.right!=null) {current=current.right;}
        		else {loseGame(playerInput, current);}
        	}
        }
        System.out.println("Is this your answer: " + current.data + "?");
        if(playerInput.nextLine().toLowerCase().trim().equals("y"))
        {
        	System.out.println("I win");
        }
        else
        {
        	loseGame(playerInput, current);
        }
    	/*
    	Scanner scanner = new Scanner(System.in);
        QuestionNode current = overallRoot;
        while ((current.left != null || current.right != null)&&current!=null) 
        {
            System.out.println(current.data);
            String response = scanner.nextLine().toLowerCase().trim();

            if (response.equals("y")) 
            {
                current = current.left;
            } 
            else if (response.equals("n")) 
            {
                current = current.right;
            }
            else 
            {
                System.out.println("Please enter 'y' or 'n'.");
            }
        }

        System.out.println("Is it " + current.data + "? (y/n)");
        String response = scanner.nextLine().toLowerCase().trim();

        if (response.equals("y")) 
        {
            System.out.println("Awesome! I win!");
        } else if (response.equals("n"))
        {
            loseGame(scanner, current);
        } else 
        {
            System.out.println("Invalid input.");
        }
        */
    }

    // Method for what happens if the game loses
    private void loseGame(Scanner scanner, QuestionNode current)
    {
        System.out.println("Boo! I lose. Please help me get better!");
        System.out.print("What is your object? ");
        String userObject = scanner.nextLine();

        System.out.print("Please give me a yes/no question to distinguish between " + userObject + " and " + current.data + ": ");
        String question = scanner.nextLine();

        System.out.print("Is the answer 'yes' for " + userObject + "? (y/n) ");
        String answer = scanner.nextLine().toLowerCase().trim();

        if (answer.equals("y")) 
        {
            current.left = new QuestionNode(userObject);
            current.right = new QuestionNode(current.data);
        } 
        else if (answer.equals("n"))
        {
            current.left = new QuestionNode(current.data);
            current.right = new QuestionNode(userObject);
        } else 
        {
            System.out.println("Invalid input.");
        }
    }

}
//Random comment