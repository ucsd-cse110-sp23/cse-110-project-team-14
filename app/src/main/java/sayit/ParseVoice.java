package sayit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * The ParseVoice class is a class that takes in a string input, which will be
 * the transcription of a voice command from the user. The ParseVoice class will
 * attempt to parse the intent of the user from their input. If the input is 
 * in regards to asking a question, then its query will also be parsed.
 * This will be used with ParseInterface.java, where it will handle calling the
 * respective methods once Intent has been parsed and determined.
 */
public class ParseVoice {
    ParseVoice(){
    }


    public String parseIntent(String input){
        String intent = "";
        Pattern question = Pattern.compile("Question" , Pattern.CASE_INSENSITIVE);
        Pattern deleteQuestion = Pattern.compile("Delete" , Pattern.CASE_INSENSITIVE);
        Pattern clearAll = Pattern.compile("Clear All" , Pattern.CASE_INSENSITIVE);
        Pattern setUpEmail = Pattern.compile("Set Up Email" , Pattern.CASE_INSENSITIVE);


         if (question.matcher(input).find()){
            intent = "Question";
            System.out.println("Intent declared as Question");
         }

         else if (deleteQuestion.matcher(input).find()){
            intent = "Delete Prompt";
            System.out.println("Intent declared as Delete");
         }

         else if (clearAll.matcher(input).find()){
            intent = "Clear All"; 
            System.out.println("Intent declared as Clear");
         } 

         else if (setUpEmail.matcher(input).find()){
            intent = "Set Up Email"; 
            System.out.println("Intent declared as Set Up Email");
         }

        return intent;
    }

    public String parseQuery(String input){
        String query = "";
        Pattern question = Pattern.compile("Question" , Pattern.CASE_INSENSITIVE);
        Pattern deleteQuestion = Pattern.compile("Delete" , Pattern.CASE_INSENSITIVE);
        Pattern clearAll = Pattern.compile("Clear",Pattern.CASE_INSENSITIVE);
        Pattern setUpEmail = Pattern.compile("Set Up Email",Pattern.CASE_INSENSITIVE);
        Matcher questionMatcher = question.matcher(input);
        Matcher deleteMatcher = deleteQuestion.matcher(input);
        Matcher clearMatcher = clearAll.matcher(input);
        Matcher setUpMatcher = setUpEmail.matcher(input);

         if (questionMatcher.find()){
            query = input.substring(questionMatcher.end() + 2);
         }

         else if (deleteMatcher.find()){
            query =  input.substring(deleteMatcher.end());
         }

         else if (clearMatcher.find()){
            query =  input.substring(clearMatcher.end());
         }
         else if (setUpMatcher.find()){
            query =  input.substring(setUpMatcher.end());
         }

        return query;
    }



    public static void main(String[] args) {
        String input = "Question. What is 3 + 3";
        ParseVoice parser = new ParseVoice();
        System.out.println(parser.parseIntent(input));
        System.out.println(parser.parseQuery(input));

        String input2 = "Delete Prompt";
        System.out.println(parser.parseIntent(input2));

        String input3 = "Clear All";
        System.out.println(parser.parseIntent(input3));
        
    }
}
