import java.util.Stack;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class EvaluateExpression extends Application {

	public static void main(String[] args) {

		Application.launch(args);

	}
	BorderPane bp = new BorderPane();

	TextField tf = new TextField();

	TextArea text = new TextArea();

	public void computeExpression(ActionEvent e) {

		// test case (5*2^3+2*3%2)*4

		// test case (1+2)*4-3

		text.clear();

		Integer result = evaluateExpression(tf.getText());

		text.appendText(result.toString());

	}

	/** Evaluate an expression */

	public int evaluateExpression(String expression) {

		// Create operandStack to store operands

		Stack<Integer> operandStack = new Stack<>();

		// Create operatorStack to store operators

		Stack<Character> operatorStack = new Stack<>();

		// Insert blanks around (, ), +, -, /, and *

		expression = insertBlanks(expression);

		// Extract operands and operators

		String[] tokens = expression.split("\\s+");

		// Phase 1 Scan tokens

		for (String token : tokens) {
			if (token.length() == 0) // Blank space
				continue; // Back to the while loop to extract the next token

			// Phase 1.2

			else if (token.charAt(0) == '+' || token.charAt(0) == '-') {
				while (!operatorStack.isEmpty()
						&& (operatorStack.peek() == '+'
						|| operatorStack.peek() == '-'
						|| operatorStack.peek() == '*'
						|| operatorStack.peek() == '/')) {

					processAnOperator(operandStack, operatorStack);
				}

				// Push the + or - operator into the operator stack
				operatorStack.push(token.charAt(0));

				// Phase 1.3
			} else if (token.charAt(0) == '*' || token.charAt(0) == '/') {

				// Process all *, /, % in the top of the operator stack

				while (!operatorStack.isEmpty()
						&& (operatorStack.peek() == '*'
						|| operatorStack.peek() == '/')) {
					processAnOperator(operandStack, operatorStack);
				}

				// Push the * or / operator into the operator stack

				operatorStack.push(token.charAt(0));

			} else if (token.trim().charAt(0) == '(') {
				operatorStack.push('('); // Push '(' to stack

				// phase 1.5

			} else if (token.trim().charAt(0) == ')') {

				// Process all the operators in the stack until seeing '('

				while (operatorStack.peek() != '(') {
					processAnOperator(operandStack, operatorStack);
				}

				operatorStack.pop(); // Pop the '(' symbol from the stack

				// phase 1.1

			} else { // An operand scanned

				// Push an operand to the stack

				operandStack.push(new Integer(token));
			}

			//output

			text.appendText("Operand stack: " + operandStack.toString() + " ");
			text.appendText("Operator stack: " + operatorStack.toString() + "\n");
		}

		// Phase 2 Clearing the stack

		//Process all the remaining operators in the stack

		while (!operatorStack.isEmpty()) {

			processAnOperator(operandStack, operatorStack);
			text.appendText("Operand stack: " + operandStack.toString() + " ");
			text.appendText("Operator stack: " + operatorStack.toString() + "\n");

		}

		// Return the result

		return operandStack.pop();

	}

	public String insertBlanks(String s) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(' || s.charAt(i) == ')' || s.charAt(i) == '+'
					|| s.charAt(i) == '-' || s.charAt(i) == '*'
					|| s.charAt(i) == '/') {
				result.append(" ");
				result.append(s.charAt(i));
				result.append(" ");
				// result += "" + s.charAt(i) + "";
			} else {
				result.append(s.charAt(i));
				// result += s.charAt(i);
			}
		}
		return result.toString();
	}

	/**
	 *
	 * Process one operator: Take an operator from operatorStack and apply it on
	 *
	 * the operands in the operandStack
	 *
	 */

	public void processAnOperator(Stack<Integer> operandStack,

			Stack<Character> operatorStack) {

		char op = operatorStack.pop();

		int op1 = operandStack.pop();

		int op2 = operandStack.pop();

		if (op == '+')

			operandStack.push(op2 + op1);

		else if (op == '-')

			operandStack.push(op2 - op1);

		else if (op == '*')

			operandStack.push(op2 * op1);

		else if (op == '/')

			operandStack.push(op2 / op1);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		HBox hbox = new HBox();
		tf.setPrefColumnCount(40);
		hbox.getChildren().addAll(new Label("Please enter arithmetic expression "), tf);
		bp.setTop(hbox);
		tf.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
		text.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 18));
		bp.setCenter(text);

		tf.setOnAction(this::computeExpression);
		Scene scene = new Scene(bp, 700, 400);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Evaluating arithmetic expression");
		primaryStage.show();
	}

}
