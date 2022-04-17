// Generated by view binder compiler. Do not edit!
package com.example.rentastic.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.rentastic.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemViewBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final TextView address;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final TextView money;

  private ItemViewBinding(@NonNull CardView rootView, @NonNull TextView address,
      @NonNull ImageView imageView, @NonNull TextView money) {
    this.rootView = rootView;
    this.address = address;
    this.imageView = imageView;
    this.money = money;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemViewBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemViewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_view, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemViewBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.address;
      TextView address = ViewBindings.findChildViewById(rootView, id);
      if (address == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.money;
      TextView money = ViewBindings.findChildViewById(rootView, id);
      if (money == null) {
        break missingId;
      }

      return new ItemViewBinding((CardView) rootView, address, imageView, money);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}