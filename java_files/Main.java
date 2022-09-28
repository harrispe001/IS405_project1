import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;
import java.io.*;



public class Main {

    public static void main(String[] args) throws IOException {
        // Get the number of teams we want as input form user
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of teams: ");
        int numTeams = sc.nextInt();

        // Read in list of students from text file
        Scanner s = new Scanner(new File("ListStudents.txt"));
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNextLine()){
            list.add(s.nextLine());
        }
        
        s.close();
        ArrayList<String> students = new ArrayList<>(list);

        // calculate num students per team
        int numStudentsPerTeam = Math.floorDiv(students.size(), numTeams);
        int numAwkStudents = students.size() % numTeams;

        // randomize the list
        Collections.shuffle(students);

        // output the groups to a .txt file
        try {

            //Delete the old file
            File outputFile = new File("NewTeams.txt");
            if (outputFile.delete()) {
                System.out.println("Generating new teams. Assignments can be found in " + outputFile.getName());
            }
            
            else {
                System.out.println("No teams found. Generating initial teams.");
            }

            //Write to the new file
            FileWriter myWriter = new FileWriter("NewTeams.txt", true);
            for (int i = 0; i < numTeams; i++) {
                String teamNum = Integer.toString(i+1);
                myWriter.write("Group " + teamNum + ":\n");

                int tempNumStudentPerTeam = numStudentsPerTeam;

                if (numAwkStudents > 0) {
                    tempNumStudentPerTeam++;
                    numAwkStudents--;
                }

                for (int j = 0; j < tempNumStudentPerTeam; j++) {
                    myWriter.write(students.get(0) + "\n");
                    students.remove(0);
                }

                myWriter.write("\n");
            }

            myWriter.close();
        }
        
        catch (IOException e) {
            System.out.println("An error occurred. Please try again.");
            e.printStackTrace();
        }

    }
}