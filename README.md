# AppsMonitor
Android App to display Apps, brought to foreground by User, after its launch.

# Support
The App uses two approaches based on the Device OS :-
1) Monitoring WINDOW_CHANGED_STATES via Accessibility-Service (APIs >= LOLLIPOP).
2) Periodically monitoring Tasks Running on Top of Activity Stack (APIs < LOLLIPOP).
