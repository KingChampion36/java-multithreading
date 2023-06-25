# Digital Image

A digital image is represented by a collection of pixels.

An image of 3036 x 4048 pixels has roughly around 12 million pixels.

A pixel is represented in `ARGB`.

- A -> Alpha(Transparency)
- R -> Red
- G -> Green
- B -> Blue

Shades of gray is achieved by keeping R, G and B equal.

Example here: [ImageProcessing.java](ImageProcessing.java)

## Summary

- We can get a speed up if we partition a problem into multiple sub-problems.
- More threads than cores is counterproductive.
- There is inherent cost for running an algorithm by multiple threads.
