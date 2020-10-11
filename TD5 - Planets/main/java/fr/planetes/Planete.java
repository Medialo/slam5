package fr.planetes;


public class Planete
{
    private String nom;
    private int distance;
    private int idImage;
    private float habitabilite;

    Planete(String nom, int distance, int idimage, float habitabilite)
    {
        this.nom = nom;
        this.distance = distance;
        this.idImage = idimage;
        this.habitabilite = habitabilite;
    }

    public Planete()
    {
        this.nom = "INCONNU";
        this.distance = 0;
        this.idImage = R.drawable.morte;
        this.habitabilite = 0;
    }

    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Planete: ");
        stringBuilder.append(nom);
        stringBuilder.append(" (");
        stringBuilder.append(this.getDistance());
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public int getDistance()
    {
        return distance;
    }

    public void setDistance(int distance)
    {
        this.distance = distance;
    }

    public int getIdImage()
    {
        return idImage;
    }

    public void setIdImage(int idImage)
    {
        this.idImage = idImage;
    }

    public float getHabitabilite() {
        return habitabilite;
    }

    public void setHabitabilite(float habitabilite) {
        this.habitabilite = habitabilite;
    }


};
