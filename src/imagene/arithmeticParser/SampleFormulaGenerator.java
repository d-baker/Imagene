/****************************
 * SampleFormulaGenerator   *
 *                          *
 * Created by Dorothea Baker*
 * for                      *
 * Programming Project 1    *
 * SP3 2016                 *
 *                          *
 * Used with permisson      *
 ****************************/

package imagene.arithmeticParser;

import java.util.*;

/*
  getFormulaArray(populationSize)
  getFormulaArray(populationSize, parent1Index, parent2Index)
  Parameters
  ----------
  - populationSize: an int specifying the number of images you want to generate.
    Vic wants the code to be written this way for extensibility, but we'll just
    be passing the number 4 for now.
  - parent1Index, parent2Index: ints specifying the index of an image in an array,
    used to identify the parents for the next generation.
    No parents are provided for the first generation, hence the overload.
  Return value
  ------------
  Both methods return a String[][] array containing the number of subarrays
  specified by populationSize.
  Each subarray consists of 3 formula strings (one for each color channel
  in the image - R,G,B).
*/

public class SampleFormulaGenerator {

    private String[] sampleFormulas = {
        "(sqrt (add (cube (add (cos rand) (sqrt y))) (cbrt (sqr (sin pi)))))",
        "(sqrt (sqr (cbrt (add (sqr rand) (sqrt y)))))",
        "(add (add (sqrt (sqrt y)) (tan (cube rand))) (cos (tan (cbrt x))))",
        "(sqr (cbrt (cbrt (sin (add rand x)))))",
        "(cos (cbrt (cbrt (sin (cbrt y)))))",
        "(tan (add (cos y) (sqr pi)))",
        "(add (sqr (cube (tan pi))) (tan (tan (cube rand))))",
        "(add (tan x) (add (cbrt rand) (cos pi)))",
        "(cube (sqr (cos (cube (cos rand)))))",
        "(cube (cos (tan y)))",
        "sin (add (sqr (sqr (sqr rand))) (sin (sqr (sqr y)))))",
        "(add (tan (sqr (cbrt x))) (tan (add (add rand x) (cos x))))",
    	"(cbrt (minus (minus (plus (plus (log (times (cos pi) (plus pi y))) (times (times (times rand x) (minus rand pi)) (minus (plus rand pi) (times y pi)))) (times (div (div (plus pi pi) (sqr pi)) (sqrt (div x y))) (plus (minus (minus pi pi) (times pi y)) (plus (div y y) (minus pi y))))) (div (cos (times (div (times pi y) (minus rand pi)) (times (times pi pi) (minus y y)))) (times (times (minus (times pi y) (div pi rand)) (cube (cube rand))) (cbrt (plus (cos pi) (plus pi pi)))))) (cube (times (log (times (plus (minus pi x) (plus pi pi)) (div (minus y pi) (div x rand)))) (plus (sin (minus (minus y pi) (plus pi rand))) (div (times (times pi pi) (minus pi pi)) (times (minus pi pi) (minus pi y))))))))",
        "(times (minus (plus (times (plus (tan (div (plus pi pi) (times x pi))) (log (div (minus x rand) (times rand pi)))) (minus (sqrt (times (minus pi y) (sqr pi))) (plus (minus (minus pi y) (minus x pi)) (times (div rand pi) (div pi pi))))) (times (times (minus (times (plus pi pi) (div pi rand)) (plus (plus rand pi) (times x x))) (times (times (sin pi) (sin pi)) (sin (sin pi)))) (minus (plus (minus (minus pi x) (minus y pi)) (div (div rand pi) (div pi rand))) (div (cbrt (tan pi)) (times (div pi pi) (div rand pi)))))) (sqr (minus (tan (plus (minus (div pi rand) (sqrt pi)) (cos (plus x pi)))) (plus (sqrt (div (cbrt pi) (times pi pi))) (div (div (minus pi pi) (plus pi pi)) (minus (times y y) (sqr pi))))))) (sin (plus (div (plus (plus (minus (times rand pi) (minus x pi)) (times (plus pi pi) (minus pi rand))) (plus (minus (log rand) (sin pi)) (div (minus pi x) (div x x)))) (div (plus (sqr (div pi pi)) (sqr (plus pi pi))) (div (cos (plus pi pi)) (plus (cos pi) (log y))))) (times (sqrt (sin (times (times y y) (cos y)))) (times (minus (times (times pi rand) (div pi rand)) (plus (plus pi pi) (times pi rand))) (times (minus (minus pi pi) (plus x rand)) (times (times rand rand) (sqr pi))))))))",
        "(cube (div (log (plus (minus (cube (minus (plus rand x) (times pi pi))) (div (plus (sqrt pi) (cube pi)) (times (times pi pi) (times pi pi)))) (cbrt (cbrt (minus (div pi pi) (plus pi y)))))) (minus (div (minus (times (minus (div y pi) (times rand x)) (times (times pi pi) (div pi x))) (times (minus (times pi pi) (sin x)) (plus (times x pi) (minus pi x)))) (minus (cbrt (minus (plus pi rand) (cube pi))) (times (minus (times pi pi) (div x y)) (minus (plus pi pi) (div x pi))))) (tan (tan (div (plus (times pi pi) (plus pi pi)) (plus (div pi pi) (minus pi pi))))))))",
        "(div (plus (times (times (plus (plus (times (times rand pi) (times pi pi)) (plus (minus pi x) (times pi pi))) (div (minus (div y x) (minus pi rand)) (times (plus pi y) (times x pi)))) (times (plus (plus (times pi rand) (tan rand)) (plus (tan y) (minus rand pi))) (cube (times (div rand pi) (div rand pi))))) (times (minus (plus (plus (cube pi) (minus y y)) (div (minus x x) (div pi pi))) (plus (div (times pi pi) (div pi pi)) (plus (plus pi pi) (cube pi)))) (times (times (minus (log pi) (plus pi pi)) (times (cos rand) (times y pi))) (times (times (sqr pi) (plus pi pi)) (cos (plus rand pi)))))) (div (plus (times (times (times (times pi pi) (div pi pi)) (div (plus x x) (cos y))) (minus (minus (times pi pi) (times y pi)) (sqr (plus x pi)))) (times (times (times (plus pi pi) (cube x)) (minus (times y pi) (div rand pi))) (times (log (minus pi pi)) (minus (div pi pi) (cos pi))))) (div (div (minus (div (plus pi pi) (div pi y)) (cube (plus pi pi))) (sqr (times (plus pi pi) (div rand pi)))) (times (plus (plus (plus pi pi) (minus pi x)) (plus (times pi x) (plus pi pi))) (div (minus (plus pi pi) (minus pi x)) (div (minus pi pi) (minus pi pi))))))) (sqr (div (minus (cbrt (plus (plus (plus pi y) (plus pi pi)) (plus (div pi pi) (times rand pi)))) (minus (plus (plus (plus pi pi) (sqr pi)) (tan (times pi pi))) (div (div (div x y) (cube pi)) (minus (div rand pi) (plus x pi))))) (div (div (div (minus (times pi pi) (plus x pi)) (plus (plus rand x) (minus rand pi))) (plus (minus (tan pi) (plus pi pi)) (times (plus x pi) (div x y)))) (times (times (plus (times x y) (cbrt rand)) (tan (cube pi))) (sqrt (minus (times pi pi) (cos pi))))))))",
        "(times (sqr (minus (minus (div (minus (log (div x rand)) (sqr (div pi pi))) (div (minus (minus pi y) (div pi pi)) (times (plus pi pi) (cube rand)))) (times (minus (times (minus pi pi) (div pi pi)) (plus (times x pi) (minus x pi))) (times (plus (tan pi) (times pi pi)) (times (times pi pi) (div pi pi))))) (div (times (div (times (div pi y) (minus pi rand)) (minus (times pi pi) (times x rand))) (div (times (div pi y) (cos pi)) (plus (minus x pi) (plus pi pi)))) (times (plus (plus (plus pi pi) (plus pi pi)) (plus (plus rand pi) (plus pi y))) (times (minus (minus x pi) (div pi x)) (times (times pi pi) (plus rand pi))))))) (plus (sqrt (plus (plus (times (div (times pi x) (minus pi y)) (times (div pi pi) (cbrt y))) (minus (times (div y pi) (cbrt pi)) (div (times pi rand) (cbrt pi)))) (plus (minus (tan (sqr pi)) (div (tan pi) (div pi y))) (plus (times (plus pi pi) (plus rand y)) (cos (div pi pi)))))) (minus (cbrt (div (times (cos (times pi pi)) (plus (div y pi) (div pi pi))) (minus (times (minus pi pi) (times pi pi)) (times (cube pi) (times pi pi))))) (minus (times (times (div (cube y) (cos x)) (minus (plus y pi) (minus x x))) (minus (times (plus pi pi) (minus pi x)) (minus (times pi rand) (plus pi pi)))) (times (cube (plus (cbrt y) (minus pi pi))) (div (minus (minus pi x) (div pi x)) (plus (minus pi pi) (minus pi pi))))))))",
        "(plus (log (cube (sqr (sqrt (times (minus (plus pi pi) (minus pi rand)) (minus (minus rand rand) (div y y))))))) (div (cube (times (tan (times (div (times x rand) (div pi pi)) (div (minus pi pi) (plus pi x)))) (plus (tan (div (tan pi) (plus x pi))) (plus (plus (div pi pi) (plus pi pi)) (cbrt (times pi pi)))))) (sqr (tan (minus (plus (div (div pi pi) (minus pi pi)) (minus (times pi pi) (plus pi y))) (minus (plus (div pi pi) (cos pi)) (plus (div pi pi) (minus pi pi))))))))",
        "(times (plus (plus (div (times (div (sin (plus pi pi)) (div (times rand pi) (times pi pi))) (div (cos (times y pi)) (div (times pi y) (plus x pi)))) (log (div (sqrt (div pi pi)) (div (div pi pi) (sqr pi))))) (div (div (times (tan (times pi rand)) (div (tan y) (plus x x))) (div (plus (plus x rand) (plus pi pi)) (minus (div y pi) (times y pi)))) (sin (div (tan (sin pi)) (div (times y pi) (minus pi pi)))))) (plus (plus (minus (cbrt (plus (minus pi pi) (sin pi))) (log (minus (log pi) (sqr pi)))) (plus (minus (div (sqrt rand) (tan x)) (times (times pi x) (minus rand pi))) (minus (div (div pi y) (cbrt pi)) (cube (div pi pi))))) (times (sin (minus (times (sqr pi) (tan pi)) (minus (div rand pi) (minus pi y)))) (log (cos (plus (times rand x) (minus pi pi))))))) (minus (div (plus (sqrt (div (plus (plus rand y) (minus pi pi)) (div (times x pi) (times rand pi)))) (minus (div (times (div pi pi) (times pi y)) (plus (div pi pi) (plus pi pi))) (minus (plus (times y x) (plus pi pi)) (sin (div y pi))))) (sqr (cbrt (times (plus (div pi rand) (cos pi)) (plus (tan x) (sqrt rand)))))) (minus (plus (times (cube (minus (minus y y) (minus pi rand))) (div (plus (plus y pi) (plus x pi)) (times (minus pi pi) (times rand pi)))) (minus (times (div (sqr y) (minus pi y)) (tan (sqrt pi))) (div (times (div pi pi) (plus pi y)) (div (sqr pi) (div pi pi))))) (minus (plus (sqr (minus (plus pi pi) (cube pi))) (sqr (sqrt (sqr pi)))) (sqrt (plus (plus (minus pi pi) (plus y pi)) (times (minus pi pi) (times pi rand))))))))",
        "(div (times (plus (plus (div (minus (times (minus y pi) (plus pi y)) (times (plus pi pi) (plus pi pi))) (plus (div (minus pi y) (plus pi pi)) (div (minus rand y) (div pi pi)))) (plus (times (minus (div pi x) (plus pi pi)) (times (minus pi x) (plus pi pi))) (sin (minus (div x pi) (plus pi pi))))) (plus (times (plus (times (minus x pi) (minus rand pi)) (sqrt (sqrt pi))) (log (times (times pi rand) (plus y x)))) (plus (minus (times (plus x y) (plus pi pi)) (div (minus pi pi) (times y pi))) (minus (div (div pi pi) (minus x pi)) (times (plus rand x) (cube pi)))))) (minus (cube (plus (minus (minus (minus pi pi) (minus x pi)) (minus (div pi x) (plus x pi))) (times (div (sqr pi) (plus pi pi)) (plus (plus pi pi) (minus rand pi))))) (minus (times (minus (minus (div pi y) (minus y pi)) (div (times x pi) (sqrt pi))) (div (minus (log x) (minus x pi)) (tan (minus pi pi)))) (sqrt (sqrt (plus (times pi pi) (plus y x))))))) (div (times (times (log (plus (sqrt (times pi pi)) (sqrt (sqrt y)))) (plus (minus (sin (minus pi pi)) (div (plus pi pi) (cbrt x))) (times (minus (div pi pi) (plus pi x)) (div (div pi pi) (sqr rand))))) (minus (div (minus (plus (times rand y) (plus pi pi)) (sqrt (minus rand x))) (sqr (plus (div rand y) (plus pi pi)))) (div (div (div (times pi pi) (plus pi rand)) (minus (times pi x) (plus rand pi))) (sqrt (times (div pi rand) (div pi pi)))))) (div (div (times (cos (minus (minus x pi) (minus y pi))) (minus (plus (minus pi pi) (minus pi pi)) (times (div rand pi) (plus pi pi)))) (times (times (sin (tan pi)) (tan (times pi pi))) (div (div (div y pi) (minus rand pi)) (minus (div rand pi) (times pi pi))))) (times (sqr (div (times (log pi) (div pi pi)) (sqr (minus y pi)))) (tan (cube (times (div pi pi) (div x pi))))))))",
        "(times (minus (plus (sqr (minus (plus (times (plus rand rand) (plus x y)) (log (times pi pi))) (div (times (plus y rand) (times pi x)) (plus (times pi x) (sqr pi))))) (times (div (minus (div (div pi pi) (minus pi pi)) (div (div pi pi) (times pi pi))) (tan (times (div pi pi) (sqr pi)))) (minus (times (minus (minus pi rand) (div pi pi)) (cube (log pi))) (div (times (plus pi pi) (cube x)) (div (sqrt pi) (plus y pi)))))) (div (times (plus (minus (cube (div pi pi)) (cbrt (times pi pi))) (cos (minus (times pi pi) (minus pi x)))) (div (times (minus (div y pi) (minus pi pi)) (minus (times pi pi) (times rand y))) (sin (cube (log pi))))) (plus (minus (div (div (plus pi pi) (plus pi pi)) (times (times pi pi) (plus pi pi))) (div (sqr (cube x)) (minus (sqr pi) (plus pi pi)))) (plus (sqr (sqrt (times pi pi))) (times (plus (minus pi y) (plus y pi)) (div (div rand rand) (div pi pi))))))) (sqrt (log (plus (times (plus (div (div pi rand) (sin pi)) (minus (plus pi y) (sqr pi))) (sqrt (div (times rand pi) (plus pi pi)))) (minus (plus (times (plus pi pi) (div rand y)) (plus (sqrt pi) (minus pi rand))) (times (minus (times pi pi) (times x pi)) (minus (minus x pi) (div rand x))))))))",
        "(minus (div (minus (cos (times (plus (tan (sqrt pi)) (times (minus pi pi) (plus rand rand))) (sqrt (div (times rand rand) (sqr rand))))) (times (plus (div (minus (sqrt rand) (plus pi pi)) (div (plus pi pi) (cube pi))) (plus (div (cube pi) (div pi pi)) (minus (plus y pi) (times pi pi)))) (times (times (times (div pi x) (cbrt pi)) (plus (cos pi) (cbrt y))) (tan (times (sqr pi) (plus x pi)))))) (cube (plus (div (sqrt (minus (plus pi pi) (plus rand y))) (div (times (times x pi) (sin pi)) (times (minus pi pi) (cube pi)))) (plus (div (times (div rand pi) (plus pi pi)) (minus (times x pi) (minus pi rand))) (sqrt (times (log pi) (minus rand pi))))))) (minus (plus (times (plus (div (log (tan pi)) (minus (minus pi rand) (plus pi pi))) (div (sqr (plus pi pi)) (plus (times y pi) (minus pi pi)))) (cos (plus (div (cos pi) (minus pi pi)) (times (div pi pi) (div pi rand))))) (div (minus (div (div (minus x y) (log y)) (plus (minus y pi) (minus pi pi))) (sqr (cube (cube y)))) (cbrt (times (sin (times pi pi)) (cos (tan pi)))))) (sqr (minus (times (sqrt (div (times x pi) (times y y))) (times (plus (minus pi pi) (minus y pi)) (minus (div pi pi) (div pi y)))) (plus (plus (div (div pi pi) (times rand pi)) (plus (minus y pi) (times pi pi))) (cbrt (times (times pi pi) (plus pi x))))))))",
        "(minus (div (times (tan (plus (div (div (div rand pi) (cube x)) (minus (minus pi pi) (minus pi pi))) (plus (plus (minus pi pi) (times pi pi)) (minus (sqrt pi) (minus pi pi))))) (minus (div (div (sin (div pi x)) (div (times pi pi) (times y pi))) (tan (sqrt (plus pi y)))) (div (times (cbrt (div y rand)) (div (div x pi) (tan x))) (minus (times (div pi pi) (plus pi pi)) (times (minus pi pi) (plus pi pi)))))) (minus (minus (minus (times (sin (sin pi)) (div (times y y) (cos pi))) (minus (div (div pi pi) (plus pi pi)) (plus (sqrt pi) (log pi)))) (cbrt (plus (times (plus pi x) (minus pi pi)) (minus (times rand x) (div pi rand))))) (cube (sqr (plus (plus (sin pi) (times rand pi)) (plus (times y pi) (minus pi pi))))))) (div (div (div (plus (plus (times (minus y pi) (cbrt y)) (times (minus rand pi) (plus pi pi))) (log (times (div pi pi) (sqr rand)))) (cos (plus (cube (div pi pi)) (div (times pi pi) (times pi pi))))) (plus (log (minus (sqr (minus rand y)) (log (minus pi pi)))) (div (plus (plus (sin x) (minus x x)) (sqrt (div pi rand))) (times (minus (plus pi x) (plus rand pi)) (times (times pi pi) (times pi pi)))))) (div (times (div (plus (tan (minus pi pi)) (minus (cbrt pi) (cube x))) (div (times (plus pi pi) (cos rand)) (times (minus pi pi) (plus pi pi)))) (sqrt (log (times (plus rand pi) (minus rand pi))))) (div (plus (sin (plus (div pi rand) (div pi pi))) (plus (sqrt (plus pi pi)) (times (log pi) (minus pi rand)))) (times (times (plus (tan pi) (plus pi pi)) (cbrt (cos pi))) (div (times (cube pi) (div pi pi)) (plus (div x pi) (plus pi y))))))))",
        "(plus (times (minus (div (cube (plus (times (log pi) (minus y pi)) (times (sqrt x) (plus pi pi)))) (div (div (minus (minus rand y) (minus x pi)) (plus (div x rand) (div pi x))) (div (times (plus pi rand) (minus x x)) (times (plus pi pi) (plus pi pi))))) (times (cos (times (plus (plus pi pi) (minus pi y)) (cos (minus pi pi)))) (times (minus (minus (plus pi pi) (div pi pi)) (times (tan pi) (minus pi pi))) (div (minus (minus x pi) (minus pi pi)) (plus (minus x y) (div pi pi)))))) (times (cbrt (div (div (times (times rand x) (minus rand pi)) (plus (times x pi) (div pi y))) (times (plus (cos pi) (plus x pi)) (times (minus y pi) (minus pi pi))))) (minus (div (div (cube (times pi pi)) (plus (minus pi pi) (cbrt rand))) (tan (plus (times pi x) (div pi rand)))) (div (div (cube (times pi x)) (times (times pi pi) (div x y))) (plus (cube (times pi x)) (cbrt (times rand pi))))))) (times (sqr (minus (tan (sqr (log (plus pi pi)))) (sqrt (cos (times (times pi pi) (plus rand pi)))))) (times (times (times (cos (tan (div rand rand))) (plus (div (minus pi pi) (sqr pi)) (times (cbrt x) (minus x pi)))) (div (minus (plus (div rand x) (times pi pi)) (plus (sin pi) (minus rand pi))) (times (minus (times pi pi) (plus pi pi)) (minus (times pi pi) (minus pi x))))) (times (div (div (div (div pi pi) (times pi y)) (plus (plus y pi) (cube pi))) (sqrt (plus (div pi x) (minus pi x)))) (minus (minus (sin (minus rand rand)) (minus (sqr y) (div pi y))) (times (plus (minus pi pi) (plus pi pi)) (minus (cube pi) (div pi pi))))))))"
    };

    // For the initial generation, we don't provide any parents.
    public String[][] getFormulaArray(int populationSize) {
        String[][] formulaArray = new String[populationSize][3];
        Random rand = new Random();

        for (int i = 0; i < populationSize; i++) {
            String[] innerArray = new String[3];
            for (int j = 0; j < 3; j++) {
                int randomIndex = rand.nextInt(sampleFormulas.length);
                innerArray[j] = sampleFormulas[randomIndex];
            }
            formulaArray[i] = innerArray;
        }

        return formulaArray;
    }

    // For subsequent generations, we pass the IDs of 2 images to be used as the parents.
    // This is just a dummy method, so it doesn't do anything with them, but
    // the real code will pass the IDs along to ECJ to do stuff with.
    public String[][] getFormulaArray(int populationSize, int parent1, int parent2) {
        return getFormulaArray(populationSize);
    }

    // Call this to get a string you can print out for testing.
    // Tells you the number of images requested and pretty-prints the formula array.
    public String getPrintableInfo(int populationSize) {
        String[][] sampleFormulaArray = getFormulaArray(populationSize);

        String output = "\nRequested number of images: " + populationSize;

        output += "\n\nFormulas: \n[\n";

        for (int i = 0; i < sampleFormulaArray.length; i++) {
            output += "    [\n";

            for (int j = 0; j < sampleFormulaArray[i].length; j++) {
                output += "        " + sampleFormulaArray[i][j];

                if (j < sampleFormulaArray[i].length - 1) { output += ","; }
                output += "\n";
            }

            output += "    ]";

            if (i < sampleFormulaArray.length - 1) { output += ",\n\n"; }
            else { output += "\n"; }
        }

        return output + "]\n";
    }

    // Call this to get a string you can print out for testing.
    // Tells you the number of images requested, parents requested,
    // and pretty-prints the formula array.
    public String getPrintableInfo(int populationSize, int parent1Index, int parent2Index) {
        return "\nRequested parent indexes: " + parent1Index + ", " + parent2Index + getPrintableInfo(populationSize);
    }
}