package au.edu.unsw.infs3634.gamifiedlearning;

import au.edu.unsw.infs3634.gamifiedlearning.models.Module;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("/module/{moduleTitle}")
    Call<Module> getModule(@Path(value = "moduleTitle", encoded = true) String moduleTitle);
}
