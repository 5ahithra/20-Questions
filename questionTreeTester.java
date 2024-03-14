import java.io.IOException;
import java.io.PrintStream;
import java.io.File;
import java.util.Scanner;

/*************************************************************
 *                                                           *
 * Make your QuestionNode class NO LONGER private and static *
 *                                                           * 
 ************************************************************/

public class questionTreeTester
{
    public static void main(String[] args) throws IOException{
        BinaryTreePrinter printer = new BinaryTreePrinter();

        Scanner file = new Scanner(new File("spec-questions.txt"));
        QuestionsGame theTree = new QuestionsGame(file);
        printer.printPreOrder(System.out, theTree.overallRoot); 
    }
}

class BinaryTreePrinter {

    public BinaryTreePrinter() {}
    
    //Assumes your nodes have data, left and right
	private String traversePreOrder(QuestionsGame.QuestionNode root) {

        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(root.data);
        
        String pointerRight ="[R]--";
        String pointerLeft = (root.right != null) ? "[L]--" : "[L]--";

        traverseNodes(sb, "", pointerLeft, root.left, root.right != null);
        
        traverseNodes(sb, "", pointerRight, root.right, false);

        return sb.toString();
    }
    private void traverseNodes(StringBuilder sb, String padding, String pointer, QuestionsGame.QuestionNode node, boolean hasRightSibling) {

        if (node != null) {

            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.data);

            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (hasRightSibling) {
                paddingBuilder.append("|    ");
            } else {
                paddingBuilder.append("     ");
            }

            String paddingForBoth = paddingBuilder.toString();
            String pointerRight = "[R]--";
            String pointerLeft = (node.right != null) ? "[L]--" : "[L]--";

            traverseNodes(sb, paddingForBoth, pointerLeft, node.left, node.right != null);
            traverseNodes(sb, paddingForBoth, pointerRight, node.right, false);

        }

    }

    public void printPreOrder(PrintStream os, QuestionsGame.QuestionNode overallRoot) {
        os.print(traversePreOrder(overallRoot));
    }


}