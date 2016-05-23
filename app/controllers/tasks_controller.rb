class TasksController < ApplicationController

  def index
    @task = Task.new
  end

  def create
    @task = current_user.tasks.create(task_params)
    redirect_to tasks_path
  end

  def edit
    @task = current_user.tasks.find(params[:id])
  end

  def update
    @task = current_user.tasks.find(params[:id])
    if @task.update(task_params)
      redirect_to tasks_path
    else
      render 'edit'
    end
  end

  private
    def task_params
      params.require(:task).permit(:due_date, :completed, :description)
    end
end
