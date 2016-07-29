package com.google.cloud.dataflow.examples.complete.game.utils;

import java.util.Collection;

import org.apache.beam.sdk.coders.Coder;
import org.apache.beam.sdk.transforms.windowing.BoundedWindow;
import org.apache.beam.sdk.transforms.windowing.WindowFn;

public class ChangeMeWindowFN<T, W extends BoundedWindow> extends WindowFn<T, W> {

	@Override
	public Collection<W> assignWindows(WindowFn<T, W>.AssignContext arg0) throws Exception {
		return null;
	}

	@Override
	public W getSideInputWindow(BoundedWindow arg0) {
		return null;
	}

	@Override
	public boolean isCompatible(WindowFn<?, ?> arg0) {
		return false;
	}

	@Override
	public void mergeWindows(WindowFn<T, W>.MergeContext arg0) throws Exception {
		
	}

	@Override
	public Coder<W> windowCoder() {
		return null;
	}

}
