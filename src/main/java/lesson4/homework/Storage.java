package lesson4.homework;

public class Storage {
    private long id;
    private String[] formatsSupported;
    private String storageCountry;
    private long storageMaxSize;

    public Storage(long id, String[] formatsSupported, String storageCountry, long storageMaxSize) {
        this.id = id;
        this.formatsSupported = formatsSupported;
        this.storageCountry = storageCountry;
        this.storageMaxSize = storageMaxSize;
    }

    public long getId() {
        return id;
    }

    public String[] getFormatsSupported() {
        return formatsSupported;
    }

    public String getStorageCountry() {
        return storageCountry;
    }

    public long getStorageMaxSize() {
        return storageMaxSize;
    }

    public String getFormatsInString(){
        String formats = "";
        for (String el : formatsSupported){
            formats += el + ",";
        }
        return formats;
    }

    public static String[] stringToFormatsArray(String formatsStr){
        return formatsStr.split(",");
    }
}
