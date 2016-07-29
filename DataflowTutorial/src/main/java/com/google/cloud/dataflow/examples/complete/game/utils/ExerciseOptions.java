package com.google.cloud.dataflow.examples.complete.game.utils;

import com.google.cloud.dataflow.examples.complete.game.Exercise1;
import com.google.cloud.dataflow.sdk.options.DataflowPipelineOptions;
import com.google.cloud.dataflow.sdk.options.Description;
import com.google.cloud.dataflow.sdk.options.PipelineOptions;
import com.google.cloud.dataflow.sdk.options.Validation;

/**
 * Options supported by {@link Exercise1}.
 */
public interface ExerciseOptions extends PipelineOptions {
  @Description("BigQuery Dataset to write tables to. Must already exist.")
  @Validation.Required
  String getDataset();
  void setDataset(String value);
}
