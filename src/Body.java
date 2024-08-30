import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Body {
    private static ArrayList<Room> rooms;

    

    private static String AppendString( String s, String addition )
    {
        if( s.isEmpty() )
        {
            return addition;
        }
        else
        {
            return s + " " + addition;
        }
    }



    private static void ParseInfo(ArrayList<String> words)
    {
        String groupName = "";
        String type = "";
        ArrayList<String> classes = new ArrayList<>();
        classes.add("");
        ArrayList<String> views = new ArrayList<>();
        views.add("");
        ArrayList<String> features = new ArrayList<>();
        features.add("");
        BedDetails bedDetails = new BedDetails();
        BedDetails.bedType bedType = BedDetails.bedType.ID;
        String bedDetailsDescription = "";
        ArrayList<String> marketingNames = new ArrayList<>();
        marketingNames.add("");
        String roomCount = "";
        String accessible = "";
        String singleUse = "";
        String sharedBed = "";
        String nonRefundable = "";
        String annex = "";
        ArrayList<String> roomNames = new ArrayList<>();
        roomNames.add("");
        String propertyName = "";

        int stage = 0;
        int alPos = 0;
        boolean required = false;
        boolean propertyEnd = false;
//        boolean first = true;
        boolean propertyNamed = false;

        for( String word : words )
        {
            if( word.equalsIgnoreCase( "colonial" ) ) ///
            {
                System.out.println();
            }

            if( propertyNamed && ! word.equals( "groups" ) )
            {
                propertyName = AppendString( propertyName, word );
            }
            else if( propertyNamed && word.equals( "groups" ) )
            {
                propertyNamed = false;
            }
            else if( word.equals( "propertyName" ) || ( word.equals( "groupName" ) && !groupName.isEmpty() ) )
            {
                if( word.equals( "groupName" ) )
                {
                    required = true;
                }

                if( stage == 14 )
                {
                    Room room = new Room();
                    room.setGroupName( groupName );
                    room.setType( type );
                    room.setClasses( classes );
                    room.setViews( views );
                    room.setFeatures( features );
                    room.setBedDetails( bedDetails );
                    room.setBedDetailsDescription( bedDetailsDescription );
                    room.setMarketingNames( marketingNames );
                    room.setRoomCount( roomCount );
                    room.setAccessible( accessible );
                    room.setSingleUse( singleUse );
                    room.setSharedBed( sharedBed );
                    room.setNonRefundable( nonRefundable );
                    room.setAnnex( annex );
                    room.setRoomNames( roomNames );
                    room.setPropertyEnd( propertyEnd );
                    room.setPropertyName( propertyName );

                    rooms.add(room);

                    groupName = "";
                    type = "";
                    classes.clear();
                    classes.add( "" );
                    views.clear();
                    views.add( "" );
                    features.clear();
                    features.add( "" );
                    bedDetails = new BedDetails();
                    bedDetailsDescription = "";
                    marketingNames.clear();
                    marketingNames.add( "" );
                    roomCount = "";
                    accessible = "";
                    singleUse = "";
                    sharedBed = "";
                    nonRefundable = "";
                    annex = "";
                    roomNames.clear();
                    roomNames.add("");
                    propertyEnd = false;
                    propertyName = "";

                    stage = 0;
                }

                if( word.equals( "propertyName" ) )
                {
                    propertyEnd = true;
                    propertyNamed = true;
                }

//                if( !first )
//                {
//                    propertyEnd = false;
//                }
//                else
//                {
//                    first = false;
//                }
            }
            else if( word.equals( "groupName" ) )
            {
                required = true;
            }
            else if( stage == 0 && word.equals( "type" ) )
            {
                stage = 1;
                required = true;
            }
            else if( stage == 1 && word.equals( "classes" ) )
            {
                stage = 2;
                alPos = 0;
                required = true;
            }
            else if( stage == 2 && word.equals( "views" ) )
            {
                stage = 3;
                alPos = 0;
                required = true;
            }
            else if( stage == 3 && word.equals( "features" ) )
            {
                stage = 4;
                alPos = 0;
                required = true;
            }
            // Sometimes bedDetails and bedDetailDescription come before marketingNames, but sometimes marketingNames comes first.
            // Hence we sometimes go via stages 4->7->5->6->8 instead of 4->5->6->7->8
            else if( ( stage == 4 || stage == 7 ) && word.equals( "bedDetails" ) )
            {
                stage = 5;
                required = true;
            }
            else if( stage == 5 && word.equals( "bedDetailDescription" ) )
            {
                stage = 6;
                required = true;
            }
            else if( ( stage == 4 || stage == 6 ) && word.equals( "marketingNames" ) )
            {
                stage = 7;
                alPos = 0;
                required = true;
            }
            else if( ( stage == 6 || stage == 7 ) && word.equals( "roomCount" ) )
            {
                stage = 8;
                required = true;
            }
            else if( stage == 8 && word.equals( "accessible" ) )
            {
                stage = 9;
                required = true;
            }
            else if( stage == 9 && word.equals( "nonRefundable" ) )
            {
                stage = 10;
                required = true;
            }
            else if( stage == 10 && word.equals( "annex" ) )
            {
                stage = 11;
                required = true;
            }
            else if( stage == 11 && word.equals( "singleUse" ) )
            {
                stage = 12;
                required = true;
            }
            else if( stage == 12 && word.equals( "sharedBed" ) )
            {
                stage = 13;
                required = true;
            }
            else if( word.equals( "roomName" ) )
            {
                if( stage == 13 )
                {
                    stage = 14;
                    alPos = 0;
                    required = true;
                }
                else if( stage == 14 )
                {
                    roomNames.add( "" );
                    alPos++;
                    required = true;
                }
            }
            else if( Arrays.asList( "groupID", "averageRoom", "roomTypes", "groupConfidence" ).contains( word ) )
            {
                required = false;
            }
            else if( required )
            {
                if( stage == 0 ) // Group name
                {
                    groupName = AppendString( groupName, word );
                }
                else if( stage == 1 ) // Type
                {
                    type = AppendString( type, word );
                }
                else if( stage == 2 ) // Classes
                {
                    if( word.equals( "," ) )
                    {
                        classes.add( "" );
                        alPos++;
                    }
                    else
                    {
                        classes.set( alPos, AppendString( classes.get( alPos ), word ) );
                    }
                }
                else if( stage == 3 ) // Views
                {
                    if( word.equals( "," ) )
                    {
                        views.add( "" );
                        alPos++;
                    }
                    else
                    {
                        views.set( alPos, AppendString( views.get( alPos ), word ) );
                    }
                }
                else if( stage == 4 ) // Features
                {
                    if( word.equals( "," ) )
                    {
                        features.add( "" );
                        alPos++;
                    }
                    else
                    {
                        features.set( alPos, AppendString( features.get( alPos ), word ) );
                    }
                }
                else if( stage == 5 ) // Bed details /////////////////////////////////////////////////////////////////// I think this may be implemented all wrong
                {
                    switch (word) {
                        case "id":
                            // This is not a bed type - it denotes a value we don't need to save
                            bedType = BedDetails.bedType.ID;
                            break;
                        case "single":
                            bedType = BedDetails.bedType.SINGLE;
                            break;
                        case "double":
                            bedType = BedDetails.bedType.DOUBLE;
                            break;
                        case "queen":
                            bedType = BedDetails.bedType.QUEEN;
                            break;
                        case "king":
                            bedType = BedDetails.bedType.KING;
                            break;
                        default:
                            // The word will be an integer
                            switch (bedType) {
                                case SINGLE:
                                    bedDetails.addSingleBeds(Integer.parseInt(word));
                                    break;
                                case DOUBLE:
                                    bedDetails.addDoubleBeds(Integer.parseInt(word));
                                    break;
                                case QUEEN:
                                    bedDetails.addQueenBeds(Integer.parseInt(word));
                                    break;
                                case KING:
                                    bedDetails.addKingBeds(Integer.parseInt(word));
                                    break;
                                case ID:
                                    break;
                                default:
                                    System.err.println( "I don't know how this was reached" );
                                    System.exit( 1 );
                            }
                            break;
                    }
                }
                else if( stage == 6 ) // Bed details description ///
                {
                    bedDetailsDescription = AppendString( bedDetailsDescription, word );
                }
                else if( stage == 7 ) // Marketing names ///
                {
                    if( word.equals( "," ) )
                    {
                        marketingNames.add( "" );
                        alPos++;
                    }
                    else
                    {
                        marketingNames.set( alPos, AppendString( marketingNames.get( alPos ), word ) );
                    }
                }
                else if( stage == 8 ) // Room count
                {
                    roomCount = AppendString( roomCount, word );
                }
                else if( stage == 9 ) // Accessible
                {
                    accessible = AppendString( accessible, word );
                }
                else if( stage == 10 ) // Non-refundable
                {
                    nonRefundable = AppendString( nonRefundable, word );
                }
                else if( stage == 11 ) // Annex
                {
                    annex = AppendString( annex, word );
                }
                else if( stage == 12 ) // Single use
                {
                    singleUse = AppendString( singleUse, word );
                }
                else if( stage == 13 ) // Shared bed
                {
                    sharedBed = AppendString( sharedBed, word );
                }
                else if( stage == 14 ) // Room names
                {
                    if( !word.equals( "roomName" ) )
                    {
                        roomNames.set( alPos, AppendString( roomNames.get( alPos ), word ) );
                    }
                }
            }
        }

        Room room = new Room();
        room.setGroupName( groupName );
        room.setType( type );
        room.setClasses( classes );
        room.setViews( views );
        room.setFeatures( features );
        room.setBedDetails( bedDetails );
        room.setBedDetailsDescription( bedDetailsDescription );
        room.setMarketingNames( marketingNames );
        room.setRoomCount( roomCount );
        room.setAccessible( accessible );
        room.setSingleUse( singleUse );
        room.setSharedBed( sharedBed );
        room.setNonRefundable( nonRefundable );
        room.setAnnex( annex );
        room.setRoomNames( roomNames );
        room.setPropertyEnd( propertyEnd );
        room.setPropertyName( propertyName );

        rooms.add( room );
    }





    private static ArrayList<String> ReadFile(String filePath)
    {
        ArrayList<String> words = new ArrayList<>();

        try {
            BufferedReader fr = new BufferedReader( new FileReader( filePath ) );
            String line;
            String word = "";
            boolean commas = false;
            boolean inString = false;

            while( ( line = fr.readLine() ) != null )
            {
                for( int i = 0; i < line.length(); i++ )
                {
                    if( line.charAt( i ) == ' ' || line.charAt( i ) == '\t' )
                    {
                        if( !word.isEmpty() )
                        {
                            if( Arrays.asList( "classes", "views", "features", "roomNames" ).contains( word ) )
                            {
                                commas = true;
                            }

                            words.add( word );
                            word = "";
                        }
                    }
                    else if( line.charAt( i ) == '"' )
                    {
                        inString = !inString;
                    }
                    else if( inString )
                    {
                        word += line.charAt( i );
                    }
                    else
                    {
                        if( Character.isLetter( line.charAt( i ) ) || Character.isDigit( line.charAt( i ) ) )
                        {
                            word += line.charAt( i );
                        }
                        else if( commas )
                        {
                            if( line.charAt( i ) == ']' )
                            {
                                commas = false;
                            }
                            else if( line.charAt( i ) == ',' )
                            {
                                words.add( word );
                                word = "";
                                words.add( "," );
                            }
                        }
                    }
                }
            }
        }
        catch( FileNotFoundException e )
        {
            System.err.println( "Unable to open file '" + filePath + "'" );
            System.exit( 1 );
        }
        catch( IOException e )
        {
            System.err.println( "Error reading file '" + filePath + "'" );
            System.exit( 1 );
        }

        return words;
    }





    private static void WriteToFile( String filePath )
    {
        try
        {
            BufferedWriter bw = new BufferedWriter( new FileWriter( filePath ) );
//            String propertyName = rooms.get( 0 ).propertyName;
            String propertyName = "";

            bw.write( "Property Name;Group Name;Type;Classes;Views;Features;Bed Details (Single,Double,Queen,King);Bed Detail Description;Marketing Names;Room Count;Accessible;Single Use;Shared Bed;Non Refundable;Annex;Room Name" );
            bw.newLine();

            for( Room room : rooms )
            {
                if( !room.propertyName.isEmpty() )
                {
                    propertyName = room.propertyName;
                }

                // Concatenate classes
                String classesString = "";
                for( int i = 0; i < room.classes.size(); i++ )
                {
                    if( i == 0 )
                    {
                        classesString = room.classes.get( i );
                    }
                    else
                    {
                        classesString += "," + room.classes.get( i );
                    }
                }

                // Concatenate views
                String viewsString = "";
                for( int i = 0; i < room.views.size(); i++ )
                {
                    if( i == 0 )
                    {
                        viewsString = room.views.get( i );
                    }
                    else
                    {
                        viewsString += "," + room.views.get( i );
                    }
                }

                // Concatenate features
                String featuresString = "";
                for( int i = 0; i < room.features.size(); i++ )
                {
                    if( i == 0 )
                    {
                        featuresString = room.features.get( i );
                    }
                    else
                    {
                        featuresString += "," + room.features.get( i );
                    }
                }

                // Concatenate marketing names
                String marketingNamesString = "";
                for( int i = 0; i < room.marketingNames.size(); i++ )
                {
                    if( i == 0 )
                    {
                        marketingNamesString = room.marketingNames.get( i );
                    }
                    else
                    {
                        marketingNamesString += "," + room.marketingNames.get( i );
                    }
                }

                for( String roomName : room.roomNames )
                {
                    bw.write( propertyName + ";" + room.groupName + ";" + room.type + ";" + classesString + ";" + viewsString + ";" + featuresString + ";" + room.bedDetails.singleBeds + "," + room.bedDetails.doubleBeds + "," + room.bedDetails.queenBeds + "," + room.bedDetails.kingBeds + ";" + room.bedDetailsDescription + ";" + marketingNamesString + ";" + room.roomCount + ";" + room.accessible + ";" + room.singleUse + ";" + room.sharedBed + ";" + room.nonRefundable + ";" + room.annex + ";" + roomName ); /// Add non-simplified columns
                    bw.newLine();
                }

//                if( ! room.propertyName.isEmpty() )
//                {
//                    propertyName = room.propertyName;
//                }
            }

            bw.flush();
            bw.close();
        }
        catch( IOException e )
        {
            System.err.println( "Error writing to file '" + filePath + "'" );
            System.exit( 1 );
        }
    }





    public static void main( String[] args )
    {
        rooms = new ArrayList<>();

        Scanner sc = new Scanner( System.in );

//        String inputFilePath = "Basic Call - Multiple Properties (003).txt";//"Test Input.txt";
//        if( args.length > 0 )
//        {
//            inputFilePath = args[0];
////            if( inputFilePath.length() < 4 || ! inputFilePath.substring( inputFilePath.length() - 4 ).equals( ".txt" ) )
//              {
////                inputFilePath += ".txt";
////            }
//        }
        System.out.print( "Enter the input file name or path:\n" );
        String inputFilePath = sc.nextLine();

        ArrayList<String> words = ReadFile( inputFilePath );

//        System.out.println(words);///////////////////////////////////////

        ParseInfo( words );

//        String outputFilePath = "Basic Call - Multiple Properties (003) Output.txt";//"Test Output.txt";
//        if( args.length > 1 )
//        {
//            outputFilePath = args[1];// + ".txt";
////            if( outputFilePath.length() < 4 || ! outputFilePath.substring( outputFilePath.length() - 4 ).equals( ".txt" ) )
//              {
////                outputFilePath += ".txt";
////            }
//        }
        System.out.print( "\nEnter the output file name or path:\n" );
        String outputFilePath = sc.nextLine();

        WriteToFile( outputFilePath );
    }
}