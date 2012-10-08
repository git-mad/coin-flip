#Creating Images with ImageViews#

## Res Directory Facts You Should Know About ##

In the res directory, you'll notice that there are 4 "drawable" folders.
Any graphical resource you're going to use for the android project
will go in the drawable folders.  These include PNG's, JPG's, GIF's.
It is recommended that you should use PNG's due to their lossless quality.

Why are there 4 drawable folders?  Android runs on many devices of
many sizes and screen resolutions (Tablets, phones, etc.). Hdpi, ldpi,
mdpi, and xhdpi are abstracted pixel density sizes to allow us to
handle which quality/sizes of images to use for whatever device we
want to develop for.  By default, the Android project will use
images from drawable-mdpi. 

The second important directory in res is layout.  The layout directory
contains only xml documents.  Android uses these xml documents create 
and format graphical components like buttons or texts.  If you know
HTML, you can see that xml used here is very similar to it.

## Setting up stuff in Res Directory ##

1. Get a heads.png and a tails.png into each drawable folder.  If
you have different quality versions of each heads.png/tails.png,
then go for it, but otherwise don't worry about it.

2. Create an ImageView Label in result.xml right before TextView result_value_label.

	<ImageView
	android:id="@+id/result_value_image"
	android:layout_height="wrap_content"
	android:layout_width="wrap_content"
	android:layout_gravity="center"
	android:layout_marginBottom="25dip" />
	
Now that you have set up all the things needed in res, the R.java file within
gen directory should be updated.  There should now be a heads and tails instances
in class drawable, and result_value_image instance in class id.

## ResultActivity.java Coding ##

The java stuff should be ready to code now.

We create a global int variable called resultImage.
We'll be assigning this variable the image id from drawable
directory, which takes on the form of an int, rather than
the image itself.

	private int resultImage = 0;
	
	...
	
	if (getResult() == "heads")
	{
		resultImage = R.drawable.heads;
	}
	else
	{
		resultImage = R.drawable.tails;
	}

Then finally, we give the resultImage id to the ImageView that we created from
result.xml (result_value_label).

	ImageView resultImageView = (ImageView) this.findViewById(R.id.result_value_image);
	resultImageView.setBackgroundResource(resultImage); 