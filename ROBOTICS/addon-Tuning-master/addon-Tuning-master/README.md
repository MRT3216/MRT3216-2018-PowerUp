# Tuning for FRC Dashboard
Addon for direct manipulation of individual NetworkTables values through FRC Dashboard.

NOTE: this addon is included in the default FRCDashboard code, so you probably shouldn't need to use this repository.

## Installation
1. Copy the contents of `tuning.html` to wherever in the dashboard you desire. For this addon, the HTML should already be on your dashboard, but it's included in case you removed it.
2. Copy `tuning.js` into the `components` directory of your FRCDashboard system.
3. Link the script from `index.html`, for example:

```html
<script src="components/tuning.js"></script>
```

4. Copy `tuning.css` to the `css` directory in your FRCDashboard system.
5. Link the CSS from `index.html`, for example:

```html
<link rel="stylesheet" href="css/tuning.css">
```

6. In `tuning.js`, replace all sources with actual sources of your camera feeds.
7. If necessary, change the keys of the NetworkTables values used in `tuning.js` to the keys your robot code uses.

Alternatively, you could just copy all the JavaScipt code into `ui.js` or other preexisting JavaScript script, but that's a bit tougher to keep organized.
