package org.apache.beam.examples.tutorial.game.utils;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.windowing.BoundedWindow;
import org.apache.beam.sdk.util.IOChannelUtils;
import org.apache.beam.sdk.util.MimeTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

/**
 * Note: this class shouldn't be used for production code. It is used for
 * debugging and working locally on Beam pipelines.
 *
 */
public class UnboundedWriteIO extends DoFn<String, Void> {
  private static final Logger LOG = LoggerFactory.getLogger(UnboundedWriteIO.class);

  /** List of output data to write */
  private transient List<String> outputs;

  /** Stream to write out file to */
  private transient BufferedOutputStream stream;

  /** Prefix of the file to write */
  protected final String prefix;

  /**
   * Constructor
   * 
   * @param prefix
   *          Prefix of the file to write
   */
  public UnboundedWriteIO(String prefix) {
    this.prefix = prefix;
  }

  @StartBundle
  public void startBundle(Context c) {
    outputs = Lists.newArrayList();
  }

  @ProcessElement
  public void processElement(ProcessContext c, BoundedWindow w) throws Exception {
    outputs.add(w.toString() + " : " + c.element());
  }

  @FinishBundle
  public void finishBundle(Context c) throws IOException {
    // Check if stream has been created yet. Initialize if not.
    if (stream == null) {
      stream = new BufferedOutputStream(Channels.newOutputStream(IOChannelUtils.create(prefix, MimeTypes.TEXT)));
    }

    // Write out all outputs
    for (String output : outputs) {
      stream.write(output.getBytes(StandardCharsets.UTF_8));
      stream.write("\n".getBytes(StandardCharsets.UTF_8));
    }

    // Clear outputs as they've been written
    outputs.clear();
  }

  @Teardown
  public void teardown() {
    try {
      stream.close();
    } catch (IOException e) {
      LOG.error("Failed to close output file.", e);
    }
  }
}
