import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        // EDIT THE VALUES OF POPULATION AND VILLAGECOUNT ACCORDING TO YOUR NEEDS
        // population is a double, so it needs somethign after the dot. For example 5.0 works, 5 does not always since it can create type errors
        // villageCount is the number of villages in the geographic area you picked. This is to both distribute that population evenly and generate a village hearth value for each of those villages.
        double population = 3.5;
        int villageCount = 24;

        // this function call generates the hearth values given the population and villageCount specified above
        randomizeProsperity(population, villageCount);

        // this function is not called right now, but it's used to compute the prosperity of a settlement given it's bound villages.
        // The way it works is that it takes the average of the bound settlements, say a castle with 2 villages. it then computes the average hearths.
        // it then compares this average to the range of values for village hearths (between 100 and 1100 currently) and computes a % value
        // representing how prosperous this area is. It then multiplies that % value to the range of values for settlement prosperity
        // (which currently are set to Towns min:1000 max:5500, Castles min:250 max:1250)
        // This final number we get will be the prosperity value of the settlement those villages are bound to.
        int[] villageProsperity = {
                793,
                482,
                533,

        };

        // this is currently commented out to allow the user to activate only either the function above to generate hearth values or this one
        // to compute a settlement's prosperity
        // TO USE THIS FUNCTION
        // EDIT THE VALUES INSIDE THE VILLAGEPROSPERITY ARRAY JUST ABOVE THIS COMMENT.
        // keep the same syntax. If you don't knwo what you're doing, just put a comma, behind each value.  If you need to put in more or less values (settlements have 1 to 4 bound settlements)
        // then just use same syntax with more or less values. Just put a comma after each value to be safe.
        //computeProsperity(villageProsperity);
    }
    public static void computeProsperity(int[] a){

        int sum = 0;
        for(int i = 0; i < a.length; i++){
            sum += a[i]-100;
        }
        double prosperity = sum/(800.0*a.length);
        int town = (int)(prosperity*4500+1000.5);
        int castle = (int)(prosperity*1000+250.5);

        System.out.println("Castle: " + castle);
        System.out.println("Town: " + town);
    }

    public static void randomizeProsperity(double population, int villageCount){

        // this line of code converts the population back to a % value
        double avgProsperity = (population*1000/villageCount -8)*0.823*8 +100;

        double tot = avgProsperity*villageCount;

        List<Integer> output = new ArrayList();

        // this is the range. i consider +-10%, for a total range of 20%, but you can increase or decrease this.
        double min = avgProsperity*0.9;
        double max = avgProsperity*1.1;

        for(int i = 1; i < villageCount; i++){
            double a = Math.random()*(max - min) + min;
            tot = tot - a;
            output.add((int)(a + 0.5));
        }
        output.add((int)(tot + 0.5));

        System.out.println("Min: " + (int)(max +0.5) + " Max: " + (int)(min +0.5) );
        for(Integer n : output){
            System.out.println(n);
        }
    }
}