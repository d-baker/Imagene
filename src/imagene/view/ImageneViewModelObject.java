package imagene.view;

import imagene.viewmodel.ImageneViewModel;

/**
 * Created by avishkar on 11/10/2016.
 * and Dorothea
 */
public class ImageneViewModelObject {

   private ImageneViewModel viewModel;

    public ImageneViewModelObject()
    {
        viewModel=ImageneViewModel.getInstance();

    }

    public ImageneViewModel getViewModel() {
        return viewModel;
    }
}
