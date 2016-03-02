The JUnit Tests for Voat and their commented user stories and scenarios are loacated in the 'src' directory.
The appropriate Selenium and JUnit jars must be included for the code to build and run.  

## User Stories and Scenarios that are covered in the JUnit tests: ##

AccountTest.java:
As a user,
I would like to have and manage an account,
so that I may customize and save my Voat experience and configurations.
	
	testLogout():
		Given that a user is logged in
		And on the front page,
		When the user selects the logout link,
		Then the user will be logged out of the site.

	testSuccessfulLogin():	
		Given the user is logging in
		And the user provides a correct user name and password,
		When the user selects to submit his/her login credentials,
		Then the user is successfully logged in.
		
	testToggleDarkMode():	
		Given the user is logged in,
		When the user selects the night mode toggle link,
		Then the page will assume the opposite theme as what they had.
		
	testIncorrectLogin():	
		Given that a user is logging in
		And the user does not provide a correct username and password,
		When the user submits his/her login credentials,
		Then a message will display noting the incorrect credentials.
		
	testSetBio():	
		Given that a user is logged in
		And on the account manage page,
		When when the user enters and saves a bio for his/her account,
		Then the bio will be set for his/her account.  


PostNavigationTest.java:
As a user,
I would like to be able to navigate posts within a subverse,
so that I may find posts that I wish to view and share.

	testNextPage():
		Given that a user is on the front page, page 0, of the /v/programming subverse,
		When the user selects the next link, 
		Then the user will be sent to the next page, page 1.
	
	testTopAllSort():
		Given that a user is on the front page, page 0, of the /v/programming subverse,
		When the user selects the Top and all link,
		Then the user will be directed to a page containing a sorted list of posts descending by number of votes.

	testNewSort():	
		Given that a user is on the front page, page 0, of the /v/programming subverse,
		When the user selects the New link,
		Then the user will be directed to a page containing a sorted list of posts descending by date.

	testSearchWithin():	
		Given that a user is on the front page, page 0, of the /v/programming subverse
		And the user has entered a search term into the search box,
		When the user submits the search term,
		Then the user will be directed to a page containing the results for the search term.

	testDefaultToHot():	
		Given that a user requests the page at address http://www.voat.co/v/programming
		When the page loads,
		Then the page will be contain the default "Hot" sorting of posts for the programming subverse.


PreviewCommentTest.java
As a user,
I would like to preview comments and replies on posts,
so that I may see what how my comment text will be formatted once submitted.

	testCommentOnPostPreviewEmpty():
		Given that a user has selected to reply to a comment in "[POST]Anchor" post
		And the user has not entered text into the subsequent comment box,
		When the user clicks on the preview button for that comment,
		Then the user will be shown a message to enter text in order to see a preview.

	testCommentOnCommentPreviewEmpty():
		Given that a user is in the comments section of the "[POST]Anchor" post
		And the user has entered text into the main comment box,
		When the user clicks on the preview button,
		Then the user will be shown a preview containing the text they entered into the comment box.

	testCommentOnPostPreview():
		Given that a user is in the comments section of the "[POST]Anchor" post
		And the user has entered text into the main comment box,
		When the user clicks on the preview button,
		Then the user will be shown a preview containing the text they entered into the comment box.

	testCommentOnCommentPreview():
		Given that a user has selected to reply to a comment in "[POST]Anchor" post
		And the user has entered text into the subsequent reply comment box,
		When the user clicks on the preview button for that reply comment,
		Then the user will be shown a preview containing the text they entered into the reply comment box.

	testCommentOnCommentCancel():	
		Given the user has entered text into a reply comment box for the "[Anchor]Comment" comment
		And is viewing a preview of the reply,
		When the user clicks on the cancel button for that reply comment,
		Then the user will be no longer see the preview or editable reply comment box.


SubverseNavigateTest.java
As a user,
I would like to be able to navigate between subverses
So that I may find the subverse that best fit my current interests.

	testExploreVoatLink():
		Given that a user is on the landing page for Voat
		And the user is not logged in,
		When the user clicks on the "Explore Voat" link,
		Then the user is directed to a page titled "Most popular subverses" 
			that contains 25 of the most popular subverses.

	testRandomSubverse():		
		Given that a user is on the landing page for Voat
		And the user is not logged in,
		When the user clicks on the "Explore Voat" link,
		Then the user is directed to a page titled "Most popular subverses" 
			that contains 25 of the most popular subverses.

	testHomeImgLink():		
		Given that a user is on the front page, page 0, of the /v/programming subverse
		And the user is not logged in,
		When the user clicks on the "Voat" image link,
		Then the user is directed to to the landing page for Voat which contains the
			title "Voat - have your say".

	testTechnologySubverseLinkFromHeader():		
		Given that a user is on the landing page for Voat
		And the user is not logged in,
		When the user clicks on the "technology" link in the header,
		Then the user is directed to the technology subverse at address "/v/technology/".

	testSubverseLinkFromPost():	
		Given that a user is on the landing page for Voat
		And the user is not logged in,
		When the user clicks on the subverse link for the first post,
		Then the user should be redirected to that subverse.





