package com.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.exam.Question;
import com.entity.exam.Quiz;
import com.service.QuestionService;
import com.service.QuizService;



@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuizService quizService;
	
	@PostMapping("/")
	public ResponseEntity<Question> addQusetion(@RequestBody Question question){
		Question question2 = questionService.addQusestion(question);
		return ResponseEntity.ok(question2);
	}
	
	
	@PutMapping("/{quesId}")
	public ResponseEntity<Question> updateQusetion(@RequestBody Question question,@PathVariable int quesId){
		question.setQuesId(quesId);
		Question question2 = questionService.updateQuestion(question);
		return ResponseEntity.ok(question2);
	}
	
	
	
	@GetMapping("/")
	public ResponseEntity<?> getQusetions(){
		 System.out.println("get questions");
		 Set<Question> set = questionService.getQuestions();
		return ResponseEntity.ok(set);
	}
	
	
	@GetMapping("/{quesId}")
	public ResponseEntity<Question> getQusetion(@PathVariable int quesId){
		Question question = questionService.getQuestion(quesId);
		return ResponseEntity.ok(question);
	}
	
	
	@GetMapping("/quiz/{qid}")
	public ResponseEntity<?> getQusetionOfQuiz(@PathVariable int qid){
		
		Quiz quiz = quizService.getQuiz(qid);
		Set<Question> questions=quiz.getQuestions();
		//Set<Question> set = questionService.getQuestionsOfQuiz(quiz);	
		List<Question> list=new ArrayList<>(questions);
		System.out.println("******* total Question present in Quiz:"+list.size()+" diplay question:"+quiz.getNumberOfQuestions()+" *****");
		if(list.size() > quiz.getNumberOfQuestions()) {
			list=list.subList(0,quiz.getNumberOfQuestions());
			System.out.println(list.size());
		}
		
		
		System.err.println(list);
		
		return ResponseEntity.ok(list);
		
		
//		Quiz quiz = quizService.getQuiz(qid);
//		Set<Question> questions=quiz.getQuestions();
//		List<Question> list=new ArrayList<>(questions);
//		
//		if(list.size() > quiz.getNumberOfQuestions()) {
//			list=list.subList(0,quiz.getNumberOfQuestions()+1);
//		}
//		Collections.shuffle(list);
//		return ResponseEntity.ok(list);
	}
	
	
	
	@DeleteMapping("/{quesId}")
	public String deleteQusetion(@PathVariable int quesId){
		questionService.deleteQuestion(quesId);
		return "delete Qusetion";
	}
	
	
	
	
	

}
