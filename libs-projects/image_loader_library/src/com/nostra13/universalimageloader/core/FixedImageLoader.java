package com.nostra13.universalimageloader.core;

//import static com.nostra13.universalimageloader.core.ImageLoader.LOG_LOAD_IMAGE_FROM_MEMORY_CACHE;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.assist.MemoryCacheUtil;
import com.nostra13.universalimageloader.utils.ImageSizeUtils;
import com.nostra13.universalimageloader.utils.L;

public class FixedImageLoader extends ImageLoader{

	protected volatile static FixedImageLoader instance;
	
	/** Returns singleton class instance */
	public static FixedImageLoader getFixedLoaderInstance() {
		if (instance == null) {
			synchronized (FixedImageLoader.class) {
				if (instance == null) {
					instance = new FixedImageLoader();
				}
			}
		}
		return instance;
	}

	protected FixedImageLoader() {
		super();
	}
	
	/**
	 * Adds display image task to execution pool. Image will be set to ImageView when it's turn.<br />
	 * <b>NOTE:</b> {@link #init(ImageLoaderConfiguration)} method must be called before this method call
	 *
	 * @param uri       Image URI (i.e. "http://site.com/image.png", "file:///mnt/sdcard/image.png")
	 * @param imageView {@link ImageView} which should display image
	 * @param options   {@linkplain DisplayImageOptions Display image options} for image displaying. If <b>null</b> -
	 *                  default display image options
	 *                  {@linkplain ImageLoaderConfiguration.Builder#defaultDisplayImageOptions(DisplayImageOptions) from
	 *                  configuration} will be used.
	 * @param listener  {@linkplain ImageLoadingListener Listener} for image loading process. Listener fires events on UI
	 *                  thread.
	 * @throws IllegalStateException    if {@link #init(ImageLoaderConfiguration)} method wasn't called before
	 * @throws IllegalArgumentException if passed <b>imageView</b> is null
	 */
	@Override
	public void displayImage(String uri, ImageView imageView, DisplayImageOptions options, ImageLoadingListener listener) {
		checkConfiguration();
		if (imageView == null) {
			throw new IllegalArgumentException(ERROR_WRONG_ARGUMENTS);
		}
		if (listener == null) {
			listener = emptyListener;
		}
		if (options == null) {
			options = configuration.defaultDisplayImageOptions;
		}

		if (TextUtils.isEmpty(uri)) {
			engine.cancelDisplayTaskFor(imageView);
			listener.onLoadingStarted(uri, imageView);
			if (options.shouldShowImageForEmptyUri()) {
				imageView.setImageResource(options.getImageForEmptyUri());
			} else {
				imageView.setImageDrawable(null);
			}
			listener.onLoadingComplete(uri, imageView, null);
			return;
		}

		ImageSize targetSize = ImageSizeUtils.defineTargetSizeForViewFixed(imageView, configuration.maxImageWidthForMemoryCache, configuration.maxImageHeightForMemoryCache);
		String memoryCacheKey = MemoryCacheUtil.generateKey(uri, targetSize);
		engine.prepareDisplayTaskFor(imageView, memoryCacheKey);

		listener.onLoadingStarted(uri, imageView);
		Bitmap bmp = configuration.memoryCache.get(memoryCacheKey);
		if (bmp != null && !bmp.isRecycled()) {
			if (configuration.writeLogs) L.d(LOG_LOAD_IMAGE_FROM_MEMORY_CACHE, memoryCacheKey);

			if (options.shouldPostProcess()) {
				ImageLoadingInfo imageLoadingInfo = new ImageLoadingInfo(uri, imageView, targetSize, memoryCacheKey, options, listener, engine.getLockForUri(uri));
				ProcessAndDisplayImageTask displayTask = new ProcessAndDisplayImageTask(engine, bmp, imageLoadingInfo, options.getHandler());
				engine.submit(displayTask);
			} else {
				options.getDisplayer().display(bmp, imageView, LoadedFrom.MEMORY_CACHE);
				listener.onLoadingComplete(uri, imageView, bmp);
			}
		} else {
			if (options.shouldShowStubImage()) {
				imageView.setImageResource(options.getStubImage());
			} else {
				if (options.isResetViewBeforeLoading()) {
					imageView.setImageDrawable(null);
				}
			}

			ImageLoadingInfo imageLoadingInfo = new ImageLoadingInfo(uri, imageView, targetSize, memoryCacheKey, options, listener, engine.getLockForUri(uri));
			LoadAndDisplayImageTask displayTask = new LoadAndDisplayImageTask(engine, imageLoadingInfo, options.getHandler());
			engine.submit(displayTask);
		}
	}
}
