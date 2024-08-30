class BedDetails {
    /* Class Variables */
    int singleBeds;
    int doubleBeds;
    int queenBeds;
    int kingBeds;



    /* enum */
    enum bedType
    {
        SINGLE,
        DOUBLE,
        QUEEN,
        KING,
        ID // This is not a bed type - it denotes a value we don't need to save
    }



    /* Class Functions */
    BedDetails() // Constructor
    {
        singleBeds = 0;
        doubleBeds = 0;
        queenBeds = 0;
        kingBeds = 0;
    }

    // Functions which add to the class variables
    void addSingleBeds( int singleBeds )
    {
        this.singleBeds += singleBeds;

    }

    void addDoubleBeds( int doubleBeds )
    {
        this.doubleBeds += doubleBeds;

    }

    void addQueenBeds( int queenBeds )
    {
        this.queenBeds += queenBeds;

    }

    void addKingBeds( int kingBeds )
    {
        this.kingBeds += kingBeds;

    }
}