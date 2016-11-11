package imagene.view;

import imagene.viewmodel.ImageneViewModel;

/*****************************************
 * Written by Avishkar Giri (s3346203)
 * and Dorothea Baker (s3367422)
 * for
 * Programming Project 1
 * SP3 2016
 ****************************************/

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
