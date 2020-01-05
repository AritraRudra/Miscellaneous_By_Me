package com.example.project;

import org.springframework.stereotype.Service;

@Service
public class NewsService {

	public News getTopStories() {
		final News news = new News();
		news.setTitle("Title");
		news.setSection("Section");
		return news;
	}
}
