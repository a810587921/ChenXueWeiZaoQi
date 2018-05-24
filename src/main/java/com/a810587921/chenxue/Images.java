package com.a810587921.chenxue;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * Created by 22 on 2018/5/24.
 */
public class Images implements Transferable {
	private Image image; //得到图片或者图片流
	public Images(Image image) {this.image = image;}

	public DataFlavor[] getTransferDataFlavors() {
		return new DataFlavor[]{DataFlavor.imageFlavor};
	}

	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return DataFlavor.imageFlavor.equals(flavor);
	}

	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		if (!DataFlavor.imageFlavor.equals(flavor)) {throw new UnsupportedFlavorException(flavor);}
		return image;
	}
}