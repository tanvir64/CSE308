public class Editor {
    private static Editor editorInstance = null;
    private Editor(){}
    public static Editor getInstance(){
        if(editorInstance == null){
            editorInstance = new Editor();
        }
        return editorInstance;
    }
    public void CreateHighlighter(String filename){                
        if(filename.equalsIgnoreCase("c")){
            CParser cparser = new CParser();
            cparser.parsing();
            AestheticsFactory aestheticsFactory = new AestheticsFactory();
            aestheticsFactory.HighlightingDetails(filename);
        }
        else if(filename.equalsIgnoreCase("cpp")){
            Parser cppparser = new CPPParser();
            cppparser.parsing();
            AestheticsFactory aestheticsFactory = new AestheticsFactory();
            aestheticsFactory.setFont("Monaco");
            aestheticsFactory.printDetails();
        }
        else if(filename.equalsIgnoreCase("py")){
            Parser pyparser = new PyParser();
            pyparser.parsing();
            AestheticsFactory aestheticsFactory = new AestheticsFactory();
            aestheticsFactory.setFont("Consolas");
            aestheticsFactory.printDetails();
        }
    }
}
