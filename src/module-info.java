/**
 * 
 */
/**
 * 
 */
module DeathByQuestion {
	exports deathbyquestion;
	
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.media;
	requires com.google.gson;
	
	opens deathbyquestion to com.google.gson;
}