import java.util.Scanner;
import java.util.StringTokenizer;

public class EditorDemo {
    public static void main(String[] args){
        System.out.println("Enter quit to terminate");
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Enter the filename you want to get parsed:");            
            String filename = scanner.nextLine();
            if(filename.equalsIgnoreCase("quit")){
                break;
            }                        
            StringTokenizer sTokenizer = new StringTokenizer(filename,".");
            String file = sTokenizer.nextToken();
            String extension = sTokenizer.nextToken();                
            Editor editorInstance = Editor.getInstance();
            editorInstance.CreateHighlighter(extension);
        }
        scanner.close();
    }
}
