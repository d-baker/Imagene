package imagene.view;
// Copyright 2006-2010 Daniel W. Dyer
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//=============================================================================


import imagene.viewmodel.ImageneViewModel;

import java.awt.*;

/*****************************************
 * Written by Avishkar Giri (s3346203)
 * and Dorothea Baker (s3367422)
 * for
 * Programming Project 1
 * SP3 2016
 ****************************************/


public class Main extends AbstractExampleGUI
{
    private ImagePanel imagePanel;
    private SettingPanel settingPanel;
    private ImageneViewModel viewModel;

    @Override
    protected void prepareGUI(Container container)
    {
        viewModel = new ImageneViewModel();

        settingPanel = new SettingPanel();
        imagePanel = new ImagePanel(viewModel);

        container.add(settingPanel, BorderLayout.WEST);
        container.add(imagePanel, BorderLayout.EAST);
    }

    public static void main(String[] args)
    {
        new Main().displayInFrame("Imagene v1.1");
    }

}
