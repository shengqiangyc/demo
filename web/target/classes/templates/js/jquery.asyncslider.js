
	/**
	 * AsyncSlider
	 * JQuery plugin
	 *
	 * Created by: Arlind Nushi
	 * Author email: arlindd@gmail.com
	 *
	 * Last Update: August 16, 2011
	 */
	
	
	(function($){
		
		// Container
		var ct;
		var p = this;
		var rotations_count = 0;
		
		// Callback Functions
		var callback = function(){};
		
		// Background Mask
		var bg_mask;
		var bg_mask_2;
		
		// Slides
		var slides = [];
		
		// Default Options
		var min_time = 1000;
		var max_time = 1200;
		var direction = 'horizontal';
		var easing = 'easeInOutExpo'; // Default
		var easing_in = '';
		var easing_out = '';
		var keyboard_navigate = true;
		var prev_next_nav = true;
		var center_prev_next_nav = true;
		var prev_next_nav_margin = 50;
		var slides_nav = true;
		var random = true;
		
		/* Version 1.1 */
		var autoswitch = false;
		var autoswitch_interval = 5000; // Default
		var autoswitch_interval_func = null;
		
		// Other Vars
		var current_index = 0;
		var total_slides = 0;
		var timeout;
		var horizontal_direction = '';
		var vertical_direction = '';
		var base_zindex = 1000;
		var busy = false;
		var _next_a, _prev_a;
		var slides_nav_el;
		
		// Slider Prepare
		function sliderPrepare(el, options)
		{
			if( typeof ct == 'undefined' )
			{
				// Define Container
				ct = $(el);
				
				// Set Container Optioms
				ct.css({position: 'relative'});
				
				// Process Options
				if( typeof options == 'object' )
				{
					// Define Direction
					if( typeof options.direction == 'string' )
					{
						if( options.direction.toLowerCase() == 'vertical' )
						{
							direction = 'vertical';
						}
						else
						{
							direction = 'horizontal';
						}
					}
					
					// Random Time
					if( typeof options.random != 'undefined' )
					{
						random = options.random;
					}
					
					// Timing Setup
					if( typeof options.minTime != 'undefined' )
					{
						min_time = options.minTime;
					}
					
					if( typeof options.maxTime != 'undefined' )
					{
						max_time = options.maxTime;
					}
					
					// Easing Setup
					if( typeo