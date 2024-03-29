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
        Pattern createEmail = Pattern.compile("Create Email" , Pattern.CASE_INSENSITIVE);
        Pattern sendEmail = Pattern.compile("Send Email" , Pattern.CASE_INSENSITIVE);
        Matcher questionMatcher = question.matcher(input);
        Matcher deleteMatcher = deleteQuestion.matcher(input);
        Matcher clearMatcher = clearAll.matcher(input);
        Matcher createEmailMatcher = createEmail.matcher(input);
        Matcher sendEmailMatcher = sendEmail.matcher(input);
        Pattern setUpEmail = Pattern.compile("Set Up Email" , Pattern.CASE_INSENSITIVE);

         if (questionMatcher.find() && questionMatcher.start() == 0){
            intent = "Question";
            System.out.println("Intent declared as Question");
         }

         else if (deleteMatcher.find() && deleteMatcher.start() == 0){
            intent = "Delete Prompt";
            System.out.println("Intent declared as Delete");
         }

         else if (clearMatcher.find() && clearMatcher.start() == 0){
            intent = "Clear All"; 
            System.out.println("Intent declared as Clear");
         } 

         else if (createEmailMatcher.find() && createEmailMatcher.start() == 0){
            intent = "Create Email";
         }

         else if (sendEmailMatcher.find() && sendEmailMatcher.start() == 0){
            intent = "Send Email";

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
        Pattern createEmail = Pattern.compile("Create Email" , Pattern.CASE_INSENSITIVE);
        Pattern sendEmail = Pattern.compile("Send Email" , Pattern.CASE_INSENSITIVE);
        Pattern setUpEmail = Pattern.compile("Set Up Email",Pattern.CASE_INSENSITIVE);
        Matcher questionMatcher = question.matcher(input);
        Matcher deleteMatcher = deleteQuestion.matcher(input);
        Matcher clearMatcher = clearAll.matcher(input);
        Matcher createEmailMatcher = createEmail.matcher(input);
        Matcher sendEmailMatcher = sendEmail.matcher(input);
        Matcher setUpMatcher = setUpEmail.matcher(input);

         if (questionMatcher.find() && questionMatcher.start() == 0){
            query = input.substring(questionMatcher.end() + 2);
         }

         else if (deleteMatcher.find() && deleteMatcher.start() == 0){
            query =  input.substring(deleteMatcher.end());
         }

         else if (clearMatcher.find() && clearMatcher.start() == 0){
            query =  input.substring(clearMatcher.end());
         }
         else if (setUpMatcher.find() && setUpMatcher.start() == 0){
            query =  input.substring(setUpMatcher.end());
         }

         else if (createEmailMatcher.find() && createEmailMatcher.start() == 0) {
            query =  input;
         }

         else if (sendEmailMatcher.find() && sendEmailMatcher.start() == 0) {
            String afterCommand = input.substring(sendEmailMatcher.end()+1);

            if (afterCommand.substring(0, input.indexOf(" ")).contains("to")) {
               query =  input.substring(sendEmailMatcher.end() + 3);
               //Replace at with @
               query = query.replace("at", "@");
               //Replace dot with .
               query = query.replace("dot", ".");
               //Remove all blank spaces
               query = query.replaceAll("\\s", "");
               
               //Remove period if there is one at the end
               if (query.charAt(query.length() - 1) == '.') {
                  query = query.substring(0, query.length() - 1);
               }
            } else {
               System.out.println("Command not in the correct format: \"Send email to ...\"");
            }
            
         }
         
         else if (createEmailMatcher.find() && createEmailMatcher.start() == 0) {
            query =  input;
         }

         else if (sendEmailMatcher.find() && sendEmailMatcher.start() == 0) {
            String afterCommand = input.substring(sendEmailMatcher.end()+1);

            if (afterCommand.substring(0, input.indexOf(" ")).contains("to")) {
               query =  input.substring(sendEmailMatcher.end() + 3);
               //Replace at with @
               query = query.replace("at", "@");
               //Replace dot with .
               query = query.replace("dot", ".");
               //Remove all blank spaces
               query = query.replaceAll("\\s", "");
               
               //Remove period if there is one at the end
               if (query.charAt(query.length() - 1) == '.') {
                  query = query.substring(0, query.length() - 1);
               }
            } else {
               System.out.println("Command not in the correct format: \"Send email to ...\"");
            }
            
         } else {
            System.out.println("Something went wrong");
         }

        return query;
    }


    public static void main(String[] args) {
        //String input = "Question. What is 3 + 3";
        ParseVoice parser = new ParseVoice();
        /*System.out.println(parser.parseIntent(input));
        System.out.println(parser.parseQuery(input));

        String input2 = "Delete Prompt";
        System.out.println(parser.parseIntent(input2));

        String input3 = "Clear All";
        System.out.println(parser.parseIntent(input3)); */

        String input4 = "Create email to Jill let's meet at Geisel for our 7pm study session to study midterm question";
        System.out.println(parser.parseIntent(input4));
        System.out.println(parser.parseQuery(input4));

        String input5 = "Send email to Jill B at UCSD dot EDU";
        System.out.println(parser.parseIntent(input5));
        System.out.println(parser.parseQuery(input5));

        String input6 = "Send email Jill B at UCSD dot EDU";
        System.out.println(parser.parseIntent(input6));
        System.out.println(parser.parseQuery(input6));
        
        
    }
}
