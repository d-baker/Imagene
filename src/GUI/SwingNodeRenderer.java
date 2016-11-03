package GUI;//=============================================================================
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

//import gp.node.*;

import javax.swing.*;
import java.awt.*;


final class NodeView extends JComponent
{



    @Override
    protected void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);


//            ImageIcon icon=new ImageIcon(image);   //java.awt
//            graphics.drawImage(icon,200,200,null);
        drawTree(graphics);
    }

    private void drawTree(Graphics graphics)
    {
        // TODO imagegen+arithmeticparser will go here!

    }

}

