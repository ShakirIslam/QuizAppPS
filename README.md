# QuizAppPS
Our approach: 

This mobile application tests students’ knowledge on INFS2603, particularly in terms of software development frameworks such as agile scrum, lean startup and design thinking. Our approach to designing this application hinged on the idea of problem based learning, with students learning through the quiz component of our app, with in built feedback features. Our custom feedback in accordance with the MediaWiki and YouTube API’s, contains further resources and content for each specific question. 

Compatibility: 

- Emulator: Nexus 5X API 27

Key functionalities: 
- Quiz component  
	- 4 categories (agile scrum, lean startup, design thinking and 	all topics) with ten randomised questions in each category
	- Each quiz tracks the progress of a student, updating the score 	with each answered question 
	- There is a 30 second time limit for each question 
	- Once a user submits an answer for a question, they have the 	option to go to the next question or gain feedback
		- A dialogue box contains custom feedback, and two 		buttons pertaining to theYouTube and MediaWiki API’s
		- The YouTube API plays a unique video pertaining to the 		particular question 
		- The MediaWiki API displays a content summary of the 		particular topic, with the option to read more, which 		takes the user to the Wikipedia page via the Google 		Chrome browser, scrolling down to the specific section 		of the page that is relevant to the question 

- Results component
	- At the conclusion of each quiz, an animated pie graph shows 		the users result
	- After a quiz is completed, the result is stored and can been 	seen through the medium of an animated line chart from 	navigating from home --> Results --> [category selection] --> Line chart of results [chosen category] Stored results can be cleared. The stored quiz results (line 	chart) had the aim of showing a broader degree of feedback, 	capturing the user's improvement. (NOTE a specific category quiz has to be completed twice to produce a line on the line graph, otherwise a single point will be shown) 

- UI/UX considerations: 
	- Design Principles: 
	- No UI deadends (User is never stuck at a screen) 
	- Produce consistent and streamline colour pallete 
	- There isn't a reliance on the native back button on mobile 	device. 
	- Landscape mode is prohibited (portrait best suits the style of 	the app) 
	- Animations are introduced to prevent a 'static' nature in the 		UX 
	- Underlying feedback features (dynamic wiki & youtube / 	animated data infographics) keep user interested and not bored 
	- Degree of randomisation (Questions are randomised such that 	user does not get bored quickly) 

Created by UNSW students Shakir Islam (z5162633) and Prerita Mehta (z5162933)
