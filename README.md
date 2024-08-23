			User Guide

Website available: https://powerhousing-aws2.onrender.com

Video demonstration: https://drive.google.com/file/d/1vfrI-KuuxaJC6VQ-q3Dv3_Rk5-Iqm1QK/view?usp=drive_link

Introduction

The PowerHousing web application is an application hosted on the AWS cloud, meaning that no installation is required. The user guide will cover login/authentication, primary use, and features of the application.

Login and Signup 
1.	Click the "Login" button in the top right corner of the app.
2.	If the user already has an account, log in with the user account name and password. If the user does not have an account and attempt to log in, the user will receive an error message notifying the user that no matching username was found. If the user would like to create an account, the user may navigate to the user creation page using the buttons on the home page or login page, or the user can navigate to the page using the link in the navigation bar.
3.	If the user needs to create an account, choose a unique username and password. By default, the username requires a minimum of 4 characters and the password requires at least 5 characters. Users must also enter a confirmation password to ensure that they did not make a type in the first password field. This function could be changed to address new password requirements. 
 

Perform Calculation
1.	Performing a calculation does not require being logged in. However, logging in will cause the application to save the application to the user’s profile for later review.
2.	Navigate to the calculator via the home page, the navigation bar, or by being redirected after logging in with user credentials.
3.	Fill out the calculator form:
  a.	Choose a wage type. Wage calculations will be performed depending on which wage type the user choose. 
    i.	Hourly wage will calculate the user’s dollar per hour wage times the number of hours worked per week, multiplied by four. This will provide a gross monthly wage based on hourly pay.
    ii.	Salaried pay will calculate the user’s monthly wages by dividing the yearly total wage by twelve to provide monthly wage based on salary. Entering the number of hours per week has no effect on this calculation because salaried workers are not paid by the hours worked.
  b.	Enter the wages based on the wage type chosen. 
  c.	Enter the user’s monthly expenses before rent. For example, the user might include car insurance, health insurance, cost of daycare, phone payments, etc. Excluding rent will allow the calculator to provide the user a sum of what is left available to pay for the user’s prospective rent. 
  d.	Choose a neighborhood in the Tampa Bay area that the user would like pricing information on. The dropdown menu contains the areas that the application currently has pricing information for. 
  i.	The Google Map to the right of the calculator will update based on the location chosen, allowing the user to visualize its proximity to the city of Tampa and the Bay area as a whole. 
  e.	Choose a housing type. The application currently contains information for the median monthly rent of one-bedroom and two-bedroom apartments, and the median full price of houses. 
  i.	The application does not calculate the monthly cost for the houses because of how mortgage and taxes will affect this amount, and that is beyond the scope of the current implementation of the application. 
4.	Click “Calculate” to submit the form with the information chosen. 
5.	Upon submitting the information, the user will be redirected to a page containing a summary of the calculations performed, including gross and net monthly wages before taxes, based on user wages and expenses. It will also contain the housing pricing information based on housing type chosen. The information contained in this calculation confirmation page is automatically saved as a “Profile” that logged in users can view at any time. 
 
Saved Profiles
1.	Saved Profiles are automatically available to all authenticated users who have an account and are currently logged in. These profiles are saved to the database upon submission of data in the calculator.
2.	The “Saved Profiles” link is always visible regardless of user authentication to let users know that the feature is available. 
3.	If the user is does not have an account or is not logged in, the Saved Profiles page will show an empty table, with only the table headers to indicate the information that is saved in a profile. 
4.	If the user is logged in, the Saved Profiles page will show a table populated with all the calculations that have been performed by that user. 
 
Reports
1.	To access the reporting feature, which contains a report of all profiles saved on all user accounts, an admin must be logged in. The link to access the report will only be available to users who have admin privileges and are currently logged in. 
2.	Once logged in and the link is visible, the admin user can click “Profile Report” to view the Jaspersoft report generated in HTML. This report shows data for all users. To export the report to PDF, the “Export to PDF” button can be clicked, and the report will be generated and saved to the user’s computer. 
 

Search
1.	To access the search feature, which by default displays a table of all calculations saved on all user accounts, an admin must be logged in. The link to access the report will only be available to users who have admin privileges and are currently logged in.
2.	Once the admin user is logged in, they can navigate to the search page using the now-visible link in the navigation bar. 
3.	On the search page, the user can use the search bar to enter desired terms, such as “One Bedroom Apartment” or “Westchase” or a specific username in order to visualize the frequency of specific search parameters or the number of calculations performed by a specific user. 
