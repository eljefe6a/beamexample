package org.apache.beam.examples.tutorial.game.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.windowing.BoundedWindow;
import org.apache.beam.sdk.util.IOChannelUtils;
import org.apache.beam.sdk.util.MimeTypes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class UnboundedWriteIO extends DoFn<String, Void> {
	private transient Map<String, List<String>> outputs;

	protected final String prefix;

	public UnboundedWriteIO(String prefix) {
		this.prefix = prefix;
	}

	@StartBundle
	public void startBundle(Context c) {
		outputs = Maps.newHashMap();
	}

	@ProcessElement
	public void processElement(ProcessContext c, BoundedWindow w) throws Exception {
		String filename = new StringBuilder().append(prefix).append("/").append(w.toString()).toString();
		List<String> values = outputs.get(filename);
		if (values == null) {
			values = Lists.newArrayList();
			outputs.put(filename, values);
		}

		values.add(c.element());
	}

	@FinishBundle
	public void finishBundle(Context c) throws IOException {
		for (String filename : outputs.keySet()) {
			OutputStream out = Channels.newOutputStream(IOChannelUtils.create(filename, MimeTypes.TEXT));
			for (String v : outputs.get(filename)) {
				out.write(v.getBytes(StandardCharsets.UTF_8));
				out.write("\n".getBytes(StandardCharsets.UTF_8));
			}
			out.close();
		}
		outputs.clear();
	}
}
