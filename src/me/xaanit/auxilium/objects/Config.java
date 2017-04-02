package me.xaanit.auxilium.objects;

public class Config {

  private String token;
  private String version;
  private String dev;
  private String dic_key;
  private String ths_key;
  private int dic_calls;
  private int the_calls;

  public Config() {}



  public void setVersion(String version) {
    this.version = version;
  }



  public void useCall(String call) {
    if (call.equals("dic"))
      this.dic_calls--;
    else
      this.the_calls--;
  }

  public int getCalls(String call) {
    if (call.equals("dic"))
      return this.dic_calls;
    else
      return this.the_calls;
  }

  public String getToken() {
    return this.token;
  }

  public String getVersion() {
    return this.version;
  }

  public String getDev() {
    return this.dev;
  }

  public String getDictionaryKey() {
    return this.dic_key;
  }

  public String getThesuarusKey() {
    return this.ths_key;
  }


}
