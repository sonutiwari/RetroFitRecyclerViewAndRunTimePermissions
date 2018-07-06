package in.co.ikai.retrofitrecyclerviewandruntimepermissions.networking;

import in.co.ikai.retrofitrecyclerviewandruntimepermissions.dataModel.JSONResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {

    @GET("tutorial/jsonparsetutorial.txt")
    Call<JSONResponse> getJSON();
}
