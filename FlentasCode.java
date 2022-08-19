import java.util.*;
import java.io.*;
import java.util.stream.Collectors; 

class Record{
     long FromMobileNumber;
     long ToMobileNumber;
     int Hours;
     int Minutes;
     int Seconds;

    public static void main(String... arr) throws IOException{

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String recordEntry = null;
    ArrayList<Record> list = new ArrayList<Record>();
    ArrayList distintFromMobileNumbers = new ArrayList<>();
    Set uniqueFromValues = null;
    StringTokenizer st = null;
    int inputFlag = 1;

    while (inputFlag == 1){
        recordEntry = br.readLine();
        st = new StringTokenizer(recordEntry, ",");
        Record r = new Record();
        r.FromMobileNumber = Long.parseLong(st.nextToken().trim());
        r.ToMobileNumber = Long.parseLong(st.nextToken().trim());
        st = new StringTokenizer(st.nextToken(), ":");
        r.Hours = Integer.valueOf(st.nextToken().trim());
        r.Minutes = Integer.valueOf(st.nextToken().trim());
        r.Seconds = Integer.valueOf(st.nextToken().trim());
        list.add(r);

        System.out.print("Press 1 to add more entries else press 0 to stop : ");
        inputFlag = Integer.parseInt(br.readLine());
    }

    // Loop for iterating ArrayList 
    for(int i = 0; i < list.size(); i++){
        if (!distintFromMobileNumbers.contains(list.get(i).FromMobileNumber))
        distintFromMobileNumbers.add(list.get(i).FromMobileNumber);
    }

     // Loop for iterating ArrayList 
    System.out.println("\nDistinct From Numbers");
    for(int i = 0; i < distintFromMobileNumbers.size(); i++)
        System.out.println(distintFromMobileNumbers.get(i));

    ArrayList<Record> distintFromMobileNumberss = new ArrayList<Record>();
    for(int i = 0; i < list.size(); i++){
        if (!distintFromMobileNumberss.contains(list.get(i)))
        distintFromMobileNumberss.add(list.get(i));
    }
    
    System.out.println("\nDistinct From Numbers Who Used Free Plan");
    for(int i = 0; i < distintFromMobileNumberss.size(); i++){
        
        if (distintFromMobileNumberss.get(i).Hours == 0 && distintFromMobileNumberss.get(i).Minutes == 0 && distintFromMobileNumberss.get(i).Seconds <= 60) {
            System.out.println(distintFromMobileNumberss.get(i).FromMobileNumber);
        }

    }

    

    System.out.println("\nTotal call duration with respect to From Number");
    System.out.println("From numbers\t\tTotal Duration");
    for(int i = 0; i < list.size(); i++){
        Record temp = list.get(i);
        if(distintFromMobileNumberss.contains(temp.FromMobileNumber)){
            long phoneNumber = temp.FromMobileNumber;
            int sec = 0;
            int min = 0;
            int hr = 0;
            for (int j = 0; j < list.size(); j++){
                
                if (list.get(j).FromMobileNumber == phoneNumber){
                    sec += list.get(j).Seconds;
                    min += list.get(j).Minutes;
                    hr += list.get(j).Hours;
                }
            }

            if (sec > 60){
                min += sec / 60;
                sec = sec % 60;
            }

            if (min > 60){
                hr += min / 60;
                min = min % 60;
            }

            System.out.println(list.get(i).FromMobileNumber + "\t\t" + hr + ":" + min + ":" + sec );
        }else{
            System.out.println(list.get(i).FromMobileNumber + "\t\t" + list.get(i).Hours + ":" + list.get(i).Minutes + ":" + list.get(i).Seconds);
          
        }

        

    }

    System.out.println("\nTotal income of the day");
    int totalCost = 0;
    for(int i = 0; i < distintFromMobileNumberss.size(); i++){
        int cost = 0;
        Record temp = distintFromMobileNumberss.get(i);

        if ( (temp.Hours>= 1) || (temp.Minutes > 1 && temp.Seconds > 0)){
            
            if (temp.Hours >= 0){
                int min = (temp.Hours * 60) + temp.Minutes;
                cost  += min * 30;
            }
        }

        totalCost += cost;

    }

    System.out.println("cost in paise :: "+ (totalCost + 30));
    System.out.println("cost in rupees :: "+ (totalCost + 30)/ 100);


    
    }
}



