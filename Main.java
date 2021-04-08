
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        int nclases=0;
        double resProbSUma = 0;
        System.out.print("¿Cuantas clases necesita?");
        nclases = leer.nextInt();
        Bayes bayes = new Bayes(nclases);
        bayes.preguntarPorcetajeClases();
        bayes.imprimirInformacion();
        System.out.println(bayes.validarPorcentajeCategorias());
        bayes.preguntarAciertoErrorCaTegoria();
        bayes.imprimirPorcentajeCategorias();
        if(bayes.validarPorcentajeAtributos()) {
            System.out.println("\ncumple con las condiciones");
        }else {
            System.out.println("\nno cumple con las condiciones");
            System.exit(0);
        }
        resProbSUma = bayes.probSuma();
        System.out.println("\nResultado probabilidad suma: "+resProbSUma);
        bayes.resultado(resProbSUma);
    }
     

}


class Bayes {
    private Scanner input = new Scanner(System.in);
    int clases;
    double porcentajeClases [];
    String nombreClases [];
    double porcentajeAcierto [];
    double porcentajeError [];
    double resultadoProbClases [];
    public Bayes(int clases) {
        this.clases = clases;
        porcentajeClases = new double[clases];
        nombreClases = new String[clases];
        porcentajeAcierto = new double[clases];
        porcentajeError = new double[clases];
        resultadoProbClases = new double[clases];
    }
    
    public void preguntarPorcetajeClases() {
        for(int i = 0; i < clases; i++) {
            System.out.print("Ingrese el nombre de la categoría:  ");
            nombreClases[i] = input.nextLine();
            
            System.out.print("Ingrese el porcentaje de la categoría, en decimal:  ");
            porcentajeClases[i] = input.nextDouble();
            input.nextLine();

        }
    }

    public void imprimirInformacion() {
        System.out.println("clases");

        for( int i = 0; i < clases; i++ ) {
            System.out.print(nombreClases[i] + ",");
        }
        System.out.println("");
        System.out.println("porcentajes");

        for( int i = 0; i < clases; i++ ) {
            System.out.print(porcentajeClases[i] + ",");
        }

    }

    public String validarPorcentajeCategorias() {
        double sumaTotal = 0;
        String resultado = "";
        for(int i = 0; i < clases; i++) {
            sumaTotal += porcentajeClases[i];
        }
        resultado = (sumaTotal==1) ? "La suma del porcentaje de clases es 100" : "No cumple";
        System.out.println("");
        if( resultado.equals("No cumple")) {
            System.out.print("EL porcentaje de las categorias no es 100");
            System.exit(0);
        }
        return resultado;
    }
   
    
    public void preguntarAciertoErrorCaTegoria(){
        for(int i=0;i<(clases);i++){
            System.out.println("Ingrese el procentaje de acierto (decimal) de la categoria   " + nombreClases[i]); 
            porcentajeAcierto[i] = input.nextDouble();
            System.out.println("Ingrese el procentaje de error (decimal) de la categoria   " + nombreClases[i]); 
            porcentajeError[i] = input.nextDouble();
        }
    }
    public void imprimirPorcentajeCategorias(){
        System.out.println("Porcentajes");

        System.out.println("\nAcierto");
        for( int i = 0; i < clases; i++ ) {
            System.out.print(porcentajeAcierto[i] + ",");          
        }  
        System.out.println("\nError");
        for( int i = 0; i < clases ;i++ ) {
            System.out.print(porcentajeError[i] + ",");
        }    
    }
    public boolean validarPorcentajeAtributos(){
        double sumaTotal=0;
        
        System.out.println("\n");
        for( int i = 0; i < clases ;i++ ) {
            sumaTotal = 0;
            sumaTotal += porcentajeError[i] + porcentajeAcierto[i];
            if( sumaTotal != 1 ){
                return false;
            }
        } 
    
        return true;
    }

    public double probSuma(){
        double res=0;
        for(int i=0 ; i< clases ; i++){
            res=porcentajeClases[i]*porcentajeError[i];
            res+=res;
        } 
        return res;
    }

    public double ProbClases(int i, double resProbSuma){
        double resProbClases=0;
        resProbClases=(porcentajeClases[i]*porcentajeError[i])/resProbSuma;
        resultadoProbClases[i] = resProbClases;
        System.out.println("Probabilidad de "+nombreClases[i]+"  =  "+resProbClases);
        return resProbClases;
    }
    
    public void resultado(double resProb) {
        double mayor = resultadoProbClases[0];
        int posicion = 0;
        for( int i = 0; i < clases; i++) {
            ProbClases(i, resProb);
        }
        for( int i = 0; i < clases; i++) {
            if(resultadoProbClases[i] > mayor) {
                mayor=resultadoProbClases[i];
                posicion = i;
            }
            
        }
        System.out.println("La mayor probabilidad que salga defectuosa es: "+mayor + "  Es la clase  " + nombreClases[posicion]);
        
    }

    

}


