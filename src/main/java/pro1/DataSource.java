package pro1;

public interface DataSource {
    public String getRozvrhByKatedra(String rok, String katedra);
    String getPredmetyByUcitel(String rok, int ucitelId, String katedra);
    public String getLiteraturaPredmetu(String zkratka, String katedra);
    public String getTerminyZkousek( String semestr,String zkratka, String katedra);
    String getTerminyZkousek2(String katedra);
    String getKvalifikacniPrace(String rokObhajoby, String katedra);
}