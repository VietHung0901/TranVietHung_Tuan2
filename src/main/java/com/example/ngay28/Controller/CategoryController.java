package com.example.ngay28.Controller;

import com.example.ngay28.Entities.Category;
import com.example.ngay28.Services.CategoryService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    @GetMapping
    public String showAllCategories(@NotNull Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "Category/list";
    }
    @GetMapping("/add")
    public String addCategoryForm(@NotNull Model model) {
        model.addAttribute("category", new Category());
        return "Category/add";
    }
    @PostMapping("/add")
    public String addCategory(@ModelAttribute("category") Category category) {
        if(categoryService.getCategoryById(category.getId()).isEmpty())
            categoryService.addCategory(category);
        return "redirect:/categories";
    }
    @GetMapping("/edit/{id}")
    public String editCategoryForm(@NotNull Model model, @PathVariable long id)
    {
        var category = categoryService.getCategoryById(id).orElse(null);
        model.addAttribute("category", category != null ? category : new Category());
        return "Category/edit";
    }
    @PostMapping("/edit")
    public String editCategory(@ModelAttribute("book") Category category) {
        categoryService.updateCategory(category);
        return "redirect:/categories";
    }
    @GetMapping("/delete/{id}")
    public String deleteCategories(@PathVariable long id) {
        if (categoryService.getCategoryById(id).isPresent())
            categoryService.deleteCategoryById(id);
        return "redirect:/categories";
    }
}
