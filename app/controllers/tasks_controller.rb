class TasksController < ApplicationController
  def create
    @task = current_user.tasks.create(task_params)
    redirect_to tasks_path(current_user)
  end

  def update
    @task = current_user.tasks.find(params[:id])
    if @task.update(task_params)
      redirect_to tasks_path(current_user)
    else
      render 'edit'
    end
  end

  private
    def task_params
      params.require(:task).permit(:due_date, :completed, :description)
    end
end
