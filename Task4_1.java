import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

// 1.Сохранить в файл строку и загрузить из файла строку с выводом в консоль используя классы 
// FileWriter и FileReader
// 2.Загрузить из файла многострочный текст формата ФИО возраст и пол через пробелы. Разбить по строкам 
// и вывести в консоль в формате "Иванов И.И. 32 М"
// 3.Загруженный и разбитый по строкам текст загрузить в подготовленные списки. Фамилии, имена, 
// отчества, возрас и пол в отдельных списках.
// 4.Отсортировать по возрасту используя дополнительный список индексов.

public class Task4_1 {

    public static void main(String[] args) {

        FileWriter fileW = null;
        FileReader fileR = null;

        // try {
        //     fileW = new FileWriter("NSL.sql");
        //     fileW.append("01 Сидоров Сергей Викторович 25 м \n");
        //     fileW.append("02 Цаплина Арина Андреевна 40 ж \n");
        //     fileW.append("03 Андрейченко Владислав Сергеевич 63 м \n");
        //     fileW.append("04 Мендоза Святослава Ануфриевна 33 ж \n");
        //     fileW.append("05 Дружинин Илья Игоревич 43 м \n");
        //     fileW.flush();

        // } catch (Exception e) {
        //     System.out.println(e.getMessage());
        // }

        String infoString = "";

        try {
            fileR = new FileReader("NSL.sql");
            while (fileR.ready()) {
                infoString+=(char)fileR.read();                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(infoString);

        String[] newInfoString = infoString.split("\n");        

        for (int i = 0; i < newInfoString.length; i++) {
            
            String[] temp = newInfoString[i].split(" ");             
            System.out.printf(temp[1] + " " + temp[2].substring(0, 1) +". " + temp[3].substring(0, 1)+". " + temp[4] + " " + temp[5].toUpperCase() + "\n");    
            }
        System.out.println();

        ArrayList<Integer> listofID = new ArrayList<>();
        ArrayList<String> listofSurname = new ArrayList<>();
        ArrayList<String> listofName = new ArrayList<>();
        ArrayList<String> listofPatronymic = new ArrayList<>();
        ArrayList<Integer> listofAge = new ArrayList<>();
        // PriorityQueue<Integer> listofAge = new PriorityQueue<>();
        ArrayList<Boolean> listofGender = new ArrayList<>();

        for (int i = 0; i < newInfoString.length; i++) {
            String[] temp = newInfoString[i].split(" ");
            listofID.add(Integer.parseInt(temp[0]));
            listofSurname.add(temp[1]);
            listofName.add(temp[2]);
            listofPatronymic.add(temp[3]);
            listofAge.add(Integer.parseInt(temp[4]));
            listofGender.add(temp[5].contains("ж")?true:false);   
                     
        }

        int[][] listForSort = new int[2][listofID.size()];
        
        for (int i = 0; i < listofID.size(); i++) {                
                listForSort[0][i]=listofID.get(i); 
                listForSort[1][i]=listofAge.get(i);                        
            }        

        for (int i = 0; i < listofID.size()-1; i++) {
            for (int j = 0; j < listofID.size()-i-1; j++) {               
            
                if (listForSort[1][j]>listForSort[1][j+1]) {

                    int temp = listForSort[1][j];
                    int temp2 = listForSort[0][j];

                    listForSort[1][j] = listForSort[1][j+1];
                    listForSort[0][j] = listForSort[0][j+1];

                    listForSort[1][j+1] = temp;
                    listForSort[0][j+1] = temp2;
                
                } 
                
            }
                                   
        }        
        
        ArrayList<Integer> sortedListOfID = new ArrayList<>();
        for (int i = 0; i < listofID.size(); i++) {
            sortedListOfID.add(listForSort[0][i]);            
        }
        
        ArrayList<String> fine = new ArrayList<>();

        for (int i = 0; i < sortedListOfID.size(); i++) {
            int index = sortedListOfID.get(i);            
            
            fine.add(listofSurname.get(index-1));
            fine.add(listofName.get(index-1));
            fine.add(listofPatronymic.get(index-1));
            fine.add(String.valueOf(listofAge.get(index-1)));
            if (listofGender.get(index-1)==true) {
                fine.add("ж");                
            } else{
                fine.add("м");
            }     
              
            fine.add("\n");          
        }

        System.out.println(fine);     

    }
}