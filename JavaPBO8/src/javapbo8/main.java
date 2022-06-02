package javapbo8;
public class main {
    public static void main(String[] args) {
        ViewMovie viewmovie = new ViewMovie();
        ModelMovie modelmovie = new ModelMovie();
        ControllerMovie controllermovie = new ControllerMovie(modelmovie, viewmovie);
        
    }
}