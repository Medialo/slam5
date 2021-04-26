package com.example.td9;


/**
 * Cette classe permet de convertir des valeurs d'une unité à l'autre
 */
@SuppressWarnings("WeakerAccess,unused")
class Convertisseur {
    /**
     * tableau des conversions : le coefficient = mCoefficients
     */
    private static final double[] mCoefficients = {
            // pouces -> cm
            2.54,
            // psi -> bar
            0.0689476,
            // mph -> km/h
            1.609344
    };
    // index dans le tableau des mCoefficients des conversions
    private int mCodeConversion;


    public Convertisseur() {
        mCodeConversion = 0;                            // BUG à laisser au début puis à corriger
    }


    /**
     * retourne le code représentant la conversion sélectionnée
     *
     * @return code de la conversion
     */
    public int getCodeConversion() {
        return mCodeConversion;                     // BUG à laisser au début puis à corriger
    }

    /**
     * définit la prochaine conversion à faire
     *
     * @param codeConversion code de la conversion
     */
    public void setCodeConversion(int codeConversion) throws IllegalArgumentException {
        if (codeConversion < 0 || codeConversion > mCoefficients.length - 1) {// BUG à laisser au début puis à corriger
            throw new IllegalArgumentException();
        }
        this.mCodeConversion = codeConversion;    // BUG à laisser au début puis à corriger
    }


    /**
     * retourne la valeur maximale que peut prendre le code de conversion
     *
     * @return code maximal, donc le code est dans 0..getCodeConversionMax()
     */
    public int getCodeConversionMax() {
        return mCoefficients.length - 1;
    }

    /**
     * effectue la conversion de la valeur d'entrée selon la conversion choisie par setCodeConversion
     *
     * @param entree valeur à convertir
     * @return valeur convertie
     */
    public double convertir(double entree) {
        double coef = mCoefficients[mCodeConversion];
        return entree * coef;
    }
}


//
///**
// * Cette classe permet de convertir des valeurs d'une unité à l'autre
// */
//@SuppressWarnings("WeakerAccess,unused")
//class Convertisseur
//{
//    // index dans le tableau des mCoefficients des conversions
//    private int mCodeConversion;
//
//
//    /**
//     * tableau des conversions : le coefficient = mCoefficients
//     */
//    private static final double[] mCoefficients = {
//            // pouces -> cm
//            2.54,
//            // psi -> bar
//            0.0689476,
//            // mph -> km/h
//            1.609344
//    };
//
//
//    public Convertisseur()
//    {
//        mCodeConversion = 0;
//    }
//
//
//    /**
//     * retourne le code représentant la conversion sélectionnée
//     * @return code de la conversion
//     */
//
//    public int getCodeConversion()
//    {
//        return mCodeConversion ;
//    }
//
//
//    /**
//     * définit la prochaine conversion à faire
//     * @param codeConversion code de la conversion
//     */
//    public void setCodeConversion(int codeConversion) throws IllegalArgumentException
//    {
//        if (codeConversion < 0 || codeConversion > mCoefficients.length) {
//            throw new IllegalArgumentException();
//        }
//        this.mCodeConversion = codeConversion;
//    }
//
//
//    /**
//     * retourne la valeur maximale que peut prendre le code de conversion
//     * @return code maximal, donc le code est dans 0..getCodeConversionMax()
//     */
//    public int getCodeConversionMax()
//    {
//        return mCoefficients.length;
//    }
//
//    /**
//     * effectue la conversion de la valeur d'entrée selon la conversion choisie par setCodeConversion
//     * @param entree valeur à convertir
//     * @return valeur convertie
//     */
//    public double convertir(double entree)
//    {
//        double coef = mCoefficients[mCodeConversion];
//        return entree * coef;
//    }
//}
