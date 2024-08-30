import java.util.ArrayList;

class Room {
    /* Class variables */
    String groupName;
    String type;
    ArrayList<String> classes;
    ArrayList<String> views;
    ArrayList<String> features;
    BedDetails bedDetails = new BedDetails();
    String bedDetailsDescription;
    ArrayList<String> marketingNames;
    String roomCount;
    String accessible;
    String singleUse;
    String sharedBed;
    String nonRefundable;
    String annex;
    ArrayList<String> roomNames;

    boolean propertyEnd;
    String propertyName;



    /* Class functions */
    Room() // Constructor
    {
        classes = new ArrayList<>();
        views = new ArrayList<>();
        features = new ArrayList<>();
        marketingNames = new ArrayList<>();
        roomNames = new ArrayList<>();
    }

    // Setters and Adders
    void setGroupName( String groupName )
    {
        this.groupName = groupName;
    }

    void setType( String type )
    {
        this.type = type;
    }

    void setClasses( ArrayList<String> classes )
    {
        this.classes.addAll( classes );
    }

    void setViews( ArrayList<String> views )
    {
        this.views.addAll( views );
    }

    void setFeatures( ArrayList<String> features )
    {
        this.features.addAll( features );
    }

    void setBedDetails( BedDetails bedDetails )
    {
        this.bedDetails = bedDetails;
    }

    void setBedDetailsDescription( String bedDetailsDescription )
    {
        this.bedDetailsDescription = bedDetailsDescription;
    }

    void setMarketingNames( ArrayList<String> marketingNames )
    {
        this.marketingNames.addAll( marketingNames );
    }

    void setRoomCount( String roomCount )
    {
        this.roomCount = roomCount;
    }

    void setAccessible( String accessible )
    {
        this.accessible = accessible;
    }

    void setSingleUse( String singleUse )
    {
        this.singleUse = singleUse;
    }

    void setSharedBed( String sharedFacilities )
    {
        this.sharedBed = sharedFacilities;
    }

    void setNonRefundable( String nonRefundable )
    {
        this.nonRefundable = nonRefundable;
    }

    void setAnnex( String annex )
    {
        this.annex = annex;
    }

    void setRoomNames( ArrayList<String> roomNames )
    {
        this.roomNames.addAll(roomNames);
    }

    void setPropertyEnd( boolean propertyEnd )
    {
        this.propertyEnd = propertyEnd;
    }

    void setPropertyName( String propertyName )
    {
        this.propertyName = propertyName;
    }
}